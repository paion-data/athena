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

@Configuration
public class BeanConfig {

    private static final String BEAN_CONFIGURATION = BeanConfig.class.toString();
    private static final Logger LOG = LoggerFactory.getLogger(BeanConfig.class);
    private static final String FILE_ID_HASHING_ALGORITHM_DEFAULT = "MD5";
    private static final String ALIOSS_ENDPOINT_KEY = "alioss_endpoint_key";
    private static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance();

    private static final String OSS_ENDPOINT = SYSTEM_CONFIG.getStringProperty(
            SYSTEM_CONFIG.getPackageVariableName(ALIOSS_ENDPOINT_KEY)
    ).orElseThrow(() -> {
        LOG.error(ErrorMessageFormat.CONFIG_NOT_FOUND.logFormat(ALIOSS_ENDPOINT_KEY));
        return new IllegalStateException(ErrorMessageFormat.CONFIG_NOT_FOUND.format());
    });

    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public AliOSSFileStore aliOssFileStore(
            @NotNull final OSS ossClient, @NotNull final FileIdGenerator fileIdGenerator
    ) {
        return new AliOSSFileStore(Objects.requireNonNull(ossClient), Objects.requireNonNull(fileIdGenerator));
    }

    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public OSS ossClient(@NotNull final EnvironmentVariableCredentialsProvider credentialsProvider) {
        return new OSSClientBuilder().build(OSS_ENDPOINT, Objects.requireNonNull(credentialsProvider));
    }

    @Bean
    public FileIdGenerator fileIdGenerator(@NotNull final MessageDigest messageDigest) {
        return new FileNameAndUploadedTimeBasedIdGenerator(messageDigest);
    }

    @Bean
    public MessageDigest messageDigest() {
        try {
            return MessageDigest.getInstance(FILE_ID_HASHING_ALGORITHM_DEFAULT);
        } catch (final NoSuchAlgorithmException exception) {
            final String message = String.format(
                    "No Provider supports a MessageDigestSpi implementation for the specified algorithm in '%s'",
                    BEAN_CONFIGURATION
            );
            LOG.error(message, exception);
            throw new IllegalStateException(message, exception);
        }
    }

    @Bean
    @ConditionalOnProperty(name = "athena.spring.alioss.enabled", havingValue = "true")
    public EnvironmentVariableCredentialsProvider credentialsProvider() {
        try {
            return CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (final ClientException exception) {
            final String message = String.format(
                    "An error occurred when the client tried to send a request or data transfer to Ali OSS in '%s'",
                    BEAN_CONFIGURATION
            );
            LOG.error(message, exception);
            throw new IllegalStateException(message, exception);
        }
    }
}
