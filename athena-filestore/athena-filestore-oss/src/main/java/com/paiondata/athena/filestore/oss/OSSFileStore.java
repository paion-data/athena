/*
 * Copyright Paion Data
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
package com.paiondata.athena.filestore.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.paiondata.athena.file.File;
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.filestore.FileStore;

import jakarta.validation.constraints.NotNull;

import java.io.InputStream;
import java.util.Objects;

/**
 * An OpenStack OSS implementation of {@link FileStore}.
 */
public class OSSFileStore implements FileStore {

    /**
     * The container name where all files are going to be stored in.
     */
    public static final String DEFAULT_BUCKET = "default-bucket";

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // 访问凭证
    private final EnvironmentVariableCredentialsProvider credentialsProvider;
    // OSS 的 Java 客户端
    private OSS ossClient;

    private final FileIdGenerator fileIdGenerator;

    public OSSFileStore(@NotNull final FileIdGenerator fileIdGenerator) throws com.aliyuncs.exceptions.ClientException {
        // 从环境变量中获取访问凭证，需要先确保已设置环境变量 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET
        this.credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        this.fileIdGenerator = fileIdGenerator;

        createOSSClient();
        createBucket();
    }

    @Override
    public String upload(final File file) {
        Objects.requireNonNull(file);
        final String fileId = getFileIdGenerator().apply(file);

        ossClient.putObject(DEFAULT_BUCKET, fileId, file.getFileContent());

        if (ossClient != null) {
            ossClient.shutdown();
        }

        return fileId;
    }

    @Override
    public InputStream download(final String fileId) {
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元数据
        final OSSObject ossObject = ossClient.getObject(DEFAULT_BUCKET, fileId);

        if (ossClient != null) {
            ossClient.shutdown();
        }

        return ossObject.getObjectContent();
    }

    // 创建 OSSClient 实例
    private void createOSSClient() {
        this.ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
    }

    // 创建存储空间
    private void createBucket() {
        final Bucket bucket = ossClient.createBucket(DEFAULT_BUCKET);
    }

    @NotNull
    private FileIdGenerator getFileIdGenerator() {
        return this.fileIdGenerator;
    }
}
