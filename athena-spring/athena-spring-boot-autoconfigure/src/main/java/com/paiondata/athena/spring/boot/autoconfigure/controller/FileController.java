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
import com.paiondata.athena.filestore.alioss.AliOSSFileStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.io.InputStream;
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
    private AliOSSFileStore aliOSSFileStore;

    /**
     * Receive a request to upload a file. Then upload the file to Ali OSS and store the file metadata in the database.
     *
     * @param file  The file content and the file metadata
     *
     * @return the generated fileId of the uploaded file
     *
     * @throws NullPointerException if {@code file} is {@code null}
     */
    @RequestMapping("/upload")
    public String upload(@NotNull final File file) {
        return aliOSSFileStore.upload(Objects.requireNonNull(file));
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
    @RequestMapping("/download")
    public InputStream download(@NotNull final String fileId) {
        return aliOSSFileStore.download(Objects.requireNonNull(fileId));
    }
}
