/*
 * Copyright 2024 Paion Data
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.paiondata.athena.spring.boot.autoconfigure.controller;

import com.paiondata.athena.file.File;
import com.paiondata.athena.filestore.FileStore;
import com.paiondata.athena.metadata.MetaData;
import com.paiondata.athena.metastore.MetaStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A controller that receives user requests to upload and download files
 * <p>
 * Users need to supply a file object as the argument to upload a file, and receive a fileId generated based on the file
 * information. Then users can find the corresponding file on Ali OSS based on the fileId and download it.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private MetaStore metaStore;

    private static final String FILE_ID = "fileId";
    private static final String FILE_CONTROLLER = FileController.class.toString();
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    /**
     * Receive a request to upload a file. Then upload the file to Ali OSS and store the file metadata in the database.
     *
     * @param file  The file content and the file metadata
     *
     * @return the generated fileId of the uploaded file
     *
     * @throws NullPointerException if {@code file} is {@code null}
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> uploadFile(@NotNull final MultipartFile file) {
        Objects.requireNonNull(file);

        final Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put(MetaData.FILE_NAME, file.getName());
        fieldMap.put(MetaData.FILE_TYPE, file.getContentType());

        try {
            final File actualFile = new File(MetaData.of(fieldMap), file.getInputStream());

            final String fileId = fileStore.upload(actualFile);
            metaStore.saveMetaData(fileId, actualFile.getMetaData());

            return Collections.singletonMap(FILE_ID, fileId);
        } catch (final IOException exception) {
            final String message = String.format(
                    "Failed or interrupted I/O operations in '%s'",
                    FILE_CONTROLLER
            );
            LOG.error(message, exception);
            throw new IllegalStateException(message, exception);
        }
    }

    /**
     * Receive a request to download a file and find the corresponding file on Ali OSS based on the fileId.
     *
     * @param fileId  The fileId of the file requested to be downloaded
     *
     * @return the inputStream of the file content
     *
     * @throws NullPointerException if {@code fileId} is {@code null}
     */
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public InputStream downloadFile(@NotNull final String fileId, @NotNull final HttpServletResponse response) {
        Objects.requireNonNull(fileId);

        response.setHeader(
                "content-disposition",
                String.format(
                        "attachment; filename = %s",
                        ((Map<?, ?>) ((Map<?, ?>) metaStore
                                .getMetaData(fileId, Collections.singletonList(MetaData.FILE_NAME))
                                .toSpecification().get("data")).get("metaData"))
                                .get(MetaData.FILE_NAME).toString()
                ));

        return fileStore.download(Objects.requireNonNull(fileId));
    }
}
