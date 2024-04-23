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

package com.paiondata.athena.spring.boot.autoconfigure.config

import com.paiondata.athena.file.identifier.FileIdGenerator
import com.paiondata.athena.file.identifier.FileNameAndUploadedTimeBasedIdGenerator
import com.paiondata.athena.filestore.FileStore
import com.paiondata.athena.metadata.MetaData
import com.paiondata.athena.metastore.MetaStore
import com.paiondata.athena.metastore.graphql.GraphQLMetaStore
import com.paiondata.athena.spring.boot.autoconfigure.controller.FileController
import com.paiondata.athena.spring.boot.autoconfigure.controller.MetaController
import com.paiondata.athena.spring.boot.autoconfigure.datafetcher.SpringSQLMutationDataFetcher
import com.paiondata.athena.spring.boot.autoconfigure.datafetcher.SpringSQLQueryDataFetcher
import com.paiondata.athena.spring.boot.autoconfigure.filestore.TestFileStore
import com.paiondata.athena.web.graphql.JacksonParser
import com.paiondata.athena.web.graphql.JsonDocumentParser

import org.apache.commons.dbcp2.BasicDataSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

import graphql.schema.DataFetcher
import jakarta.inject.Named
import jakarta.validation.constraints.NotNull
import spock.lang.Specification

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import javax.sql.DataSource

@TestConfiguration
class TestConfig extends Specification{
    static final String TEST_CONFIGURATION = TestConfig.class.getName()
    static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
    static final String FILE_ID_HASHING_ALGORITHM_DEFAULT = "MD5"

    @Bean
    FileController fileController(@NotNull final FileStore testFileStore, @NotNull final MetaStore graphQLMetaStore) {
        def fileController = new FileController(fileStore: testFileStore, metaStore: graphQLMetaStore)

//        fileController.metaClass.setProperty(fileController.class, "fileStore", testFileStore)
//        fileController.metaClass.setProperty(fileController.class, "metaStore", graphQLMetaStore)

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
//        return TestFileStore.class;
        return new TestFileStore(fileIdGenerator)
    }

    @Bean
    FileIdGenerator fileIdGenerator(@NotNull final MessageDigest messageDigest) {
        return new FileNameAndUploadedTimeBasedIdGenerator(messageDigest);
    }

    @Bean
    MessageDigest messageDigest() {
        try {
            return MessageDigest.getInstance(FILE_ID_HASHING_ALGORITHM_DEFAULT);
        } catch (final NoSuchAlgorithmException exception) {
            final String message = String.format(
                    "No Provider supports a MessageDigestSpi implementation for the specified algorithm in '%s'",
                    TEST_CONFIGURATION
            );
            LOG.error(message, exception)
            throw new IllegalStateException(message, exception)
        }
    }

    @Bean
    JsonDocumentParser jsonDocumentParser() {
        return JacksonParser.getInstance()
    }

    @Bean
    MetaStore graphQLMetaStore(
            @NotNull final @Named("queryDataFetcher") DataFetcher<MetaData> queryDataFetcher,
            @NotNull final @Named("mutationDataFetcher") DataFetcher<MetaData> mutationDataFetcher
    ) {
        return new GraphQLMetaStore(queryDataFetcher, mutationDataFetcher)
    }

    @Bean
    DataFetcher<MetaData> queryDataFetcher(@NotNull final DataSource dataSource) {
        return new SpringSQLQueryDataFetcher(dataSource)
    }

    @Bean
    DataFetcher<MetaData> mutationDataFetcher(@NotNull final DataSource dataSource) {
        return new SpringSQLMutationDataFetcher(dataSource)
    }

    @Bean
    DataSource dataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver")
        basicDataSource.setUrl("jdbc:derby:memory:Athena;create=true")

        return basicDataSource;
    }
}
