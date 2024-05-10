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
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.filestore.FileStore;
import com.paiondata.athena.filestore.alioss.AliOSSFileStore;
import com.paiondata.athena.spring.boot.autoconfigure.TestOss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotNull;

/**
 * Overriding production configs.
 */
@Configuration
public class FileControllerITConfig {

    /**
     * Use an in-memory OSS.
     *
     * @return a new instance
     */
    @Bean
    OSS testClient() {
        return new TestOss();
    }

    /**
     * Uses an in-memory file store.
     *
     * @param ossClient  The injected file storage client
     * @param fileIdGenerator  The file ID generator
     *
     * @return a new instance
     */
    @Bean
    FileStore fileStore(@NotNull final OSS ossClient, @NotNull final FileIdGenerator fileIdGenerator) {
        return new AliOSSFileStore(ossClient, fileIdGenerator);
    }
}
