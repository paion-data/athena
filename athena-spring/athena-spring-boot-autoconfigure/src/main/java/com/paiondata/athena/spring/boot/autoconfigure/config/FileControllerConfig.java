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
package com.paiondata.athena.spring.boot.autoconfigure.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import com.paiondata.athena.config.ErrorMessageFormat;
import com.paiondata.athena.config.SystemConfig;
import com.paiondata.athena.config.SystemConfigFactory;
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.file.identifier.FileNameAndUploadedTimeBasedIdGenerator;
import com.paiondata.athena.filestore.FileStore;
import com.paiondata.athena.filestore.alioss.AliOSSFileStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * A configuration class responsible for injecting all the beans required by fileController
 * <p>
 * The key bean injected in this class is aliOssFileStore. This bean needs ossClient and fileIdGenerator as arguments.
 * Users can provide the required configuration information when injecting ossClient.
 */
@Configuration
public class FileControllerConfig {

    private static final String FILE_CONFIGURATION = FileControllerConfig.class.getName();
    private static final Logger LOG = LoggerFactory.getLogger(FileControllerConfig.class);
    private static final String FILE_ID_HASHING_ALGORITHM_DEFAULT = "MD5";
    private static final String ALIOSS_ENDPOINT_KEY = "alioss_endpoint_key";
    private static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance();

    private static final String OSS_ENDPOINT = SYSTEM_CONFIG.getStringProperty(
            SYSTEM_CONFIG.getPackageVariableName(ALIOSS_ENDPOINT_KEY)
    ).orElseThrow(() -> {
        LOG.error(ErrorMessageFormat.CONFIG_NOT_FOUND.logFormat(ALIOSS_ENDPOINT_KEY));
        return new IllegalStateException(ErrorMessageFormat.CONFIG_NOT_FOUND.format());
    });

    /**
     * Inject aliOssFileStore.
     *
     * @param ossClient  An Ali OSS Java client for managing OSS resources such as storage space and files. To initiate
     * an OSS request using the Java SDK, you need to initialize an OSSClient instance and modify the default
     * configuration items of the ClientConfiguration as needed.
     * @param fileIdGenerator  An object that provides file unique identifiers.
     *
     * @return a fileStore for uploading and downloading files in Ali OSS
     *
     * @throws NullPointerException if any constructor argument is {@code null}
     */
    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public FileStore aliOssFileStore(
            @NotNull final OSS ossClient, @NotNull final FileIdGenerator fileIdGenerator
    ) {
        return new AliOSSFileStore(Objects.requireNonNull(ossClient), Objects.requireNonNull(fileIdGenerator));
    }

    /**
     * Inject ossClient when the athena.spring.alioss.enabled configuration item is true.
     *
     * @param credentialsProvider  Used to obtain the access credentials from environment variables.
     *
     * @return an Ali OSS Java client
     *
     * @throws NullPointerException if {@code credentialsProvider} is {@code null}
     */
    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public OSS ossClient(@NotNull final EnvironmentVariableCredentialsProvider credentialsProvider) {
        return new OSSClientBuilder().build(OSS_ENDPOINT, Objects.requireNonNull(credentialsProvider));
    }

    /**
     * Inject fileIdGenerator.
     *
     * @param messageDigest  An information summarization algorithm is provided.
     *
     * @return a FileIdGenerator generated based on a specified digest algorithm
     */
    @Bean
    public FileIdGenerator fileIdGenerator(@NotNull final MessageDigest messageDigest) {
        return new FileNameAndUploadedTimeBasedIdGenerator(Objects.requireNonNull(messageDigest));
    }

    /**
     * Inject messageDigest according to the preset algorithm name to get a MessageDigest instance that provides the
     * corresponding algorithm.
     *
     * @return a MessageDigest instance
     *
     * @throws IllegalStateException if the particular cryptographic algorithm is requested but is not available in the
     * environment.
     */
    @Bean
    public MessageDigest messageDigest() {
        try {
            return MessageDigest.getInstance(FILE_ID_HASHING_ALGORITHM_DEFAULT);
        } catch (final NoSuchAlgorithmException exception) {
            final String message = String.format(
                    "No Provider supports a MessageDigestSpi implementation for the specified algorithm in '%s'",
                    FILE_CONFIGURATION
            );
            LOG.error(message, exception);
            throw new IllegalStateException(message, exception);
        }
    }

    /**
     * Inject credentialsProvider when the athena.spring.alioss.enabled configuration item is true. Obtain the
     * user-configured access credentials from the environment variable and inject them.
     *
     * @return an EnvironmentVariableCredentialsProvider loaded with the required access configuration
     *
     * @throws IllegalStateException if the client fails to send a request to OSS or transmit data.
     */
    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public EnvironmentVariableCredentialsProvider credentialsProvider() {
        try {
            return CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (final ClientException exception) {
            final String message = String.format(
                    "An error occurred when the client tried to send a request or data transfer to Ali OSS in '%s'",
                    FILE_CONFIGURATION
            );
            LOG.error(message, exception);
            throw new IllegalStateException(message, exception);
        }
    }
}
