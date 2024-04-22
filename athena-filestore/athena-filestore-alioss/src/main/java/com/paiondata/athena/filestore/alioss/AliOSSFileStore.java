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
package com.paiondata.athena.filestore.alioss;

import com.aliyun.oss.OSS;
import com.paiondata.athena.file.File;
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.filestore.FileStore;

import jakarta.validation.constraints.NotNull;

import java.io.InputStream;
import java.util.Objects;

/**
 * A AliCloud OSS implementation of {@link FileStore}.
 * <p>
 * Developers must provide an instance of the OSSClient class and a FileIdGenerator instance when instantiating an
 * AliOSSFileStore object. To upload files, they can utilize the upload method by supplying a
 * com.paiondata.athena.file.File object as an argument. To download files, they should invoke the download method and
 * furnish it with a FileId that uniquely identifies the file to be retrieved from the cloud storage service.
 */
public class AliOSSFileStore implements FileStore {

    /**
     * The container name where all files are going to be stored in.
     */
    public static final String DEFAULT_BUCKET = "default-bucket";
    private final OSS ossClient;
    private final FileIdGenerator fileIdGenerator;

    public AliOSSFileStore(@NotNull final OSS ossClient, @NotNull final FileIdGenerator fileIdGenerator) {
        this.ossClient = Objects.requireNonNull(ossClient);
        this.fileIdGenerator = Objects.requireNonNull(fileIdGenerator);
    }

    @Override
    public String upload(final File file) {
        Objects.requireNonNull(file);
        final String fileId = getFileIdGenerator().apply(file);

        getOSSClient()
                .putObject(DEFAULT_BUCKET, fileId, file.getFileContent());

        return fileId;
    }

    @Override
    public InputStream download(final String fileId) {
        return getOSSClient()
                .getObject(DEFAULT_BUCKET, fileId)
                .getObjectContent();
    }

    @NotNull
    private OSS getOSSClient() {
        return this.ossClient;
    }

    @NotNull
    private FileIdGenerator getFileIdGenerator() {
        return this.fileIdGenerator;
    }
}
