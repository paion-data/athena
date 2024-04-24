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

package com.paiondata.athena.spring.boot.autoconfigure.config

import com.paiondata.athena.file.identifier.FileIdGenerator
import com.paiondata.athena.filestore.FileStore
import com.paiondata.athena.metastore.MetaStore
import com.paiondata.athena.spring.boot.autoconfigure.controller.FileController
import com.paiondata.athena.spring.boot.autoconfigure.controller.MetaController
import com.paiondata.athena.spring.boot.autoconfigure.filestore.TestFileStore
import com.paiondata.athena.web.graphql.JsonDocumentParser

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

import jakarta.validation.constraints.NotNull
import spock.lang.Specification

@TestConfiguration
class TestConfig extends Specification{
    static final String TEST_CONFIGURATION = TestConfig.class.getName()
    static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
    static final String FILE_ID_HASHING_ALGORITHM_DEFAULT = "MD5"

    @Bean
    FileController fileController(@NotNull final FileStore testFileStore, @NotNull final MetaStore graphQLMetaStore) {
        def fileController = new FileController(fileStore: testFileStore, metaStore: graphQLMetaStore)

        return fileController
    }

    @Bean
    MetaController metaController(@NotNull final MetaStore graphQLMetaStore, @NotNull final JsonDocumentParser jsonDocumentParser) {
        def metaController = new MetaController()
        metaController.metaClass.setProperty(metaController.class, "metaStore", graphQLMetaStore)
        metaController.metaClass.setProperty(metaController.class, "jsonDocumentParser", jsonDocumentParser)

        return metaController
    }

    @Bean
    FileStore testFileStore(@NotNull final FileIdGenerator fileIdGenerator) {
        return new TestFileStore(fileIdGenerator)
    }
}