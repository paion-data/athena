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

import com.paiondata.athena.metadata.MetaData;
import com.paiondata.athena.metastore.MetaStore;
import com.paiondata.athena.metastore.graphql.GraphQLMetaStore;
import com.paiondata.athena.spring.boot.autoconfigure.datafetcher.SpringSQLMutationDataFetcher;
import com.paiondata.athena.spring.boot.autoconfigure.datafetcher.SpringSQLQueryDataFetcher;
import com.paiondata.athena.web.graphql.JacksonParser;
import com.paiondata.athena.web.graphql.JsonDocumentParser;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.DataFetcher;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import javax.sql.DataSource;

/**
 * A configuration class responsible for injecting all the beans required by metaController
 * <p>
 * The key bean injected in this class is graphQLMetaStore. This bean needs queryDataFetcher and mutationDataFetcher as
 * arguments.
 */
@Configuration
public class MetaControllerConfig {

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Inject jsonDocumentParser.
     *
     * @return An object used to extract metadata request information from the body of the POST request, such as
     * the file ID and metadata fields requested by the client.
     */
    @Bean
    public JsonDocumentParser jsonDocumentParser() {
        return JacksonParser.getInstance();
    }

    /**
     * Inject graphQLMetaStore.
     *
     * @param queryDataFetcher  Query file metadata from a SQL data storage via a {@link DataSource}.
     * @param mutationDataFetcher  Save file metadata into a SQL data storage via a {@link DataSource}.
     *
     * @return the default implementation of {@link MetaStore}.
     *
     * @throws NullPointerException if any constructor argument is {@code null}
     */
    @Bean
    public MetaStore graphQLMetaStore(
            @NotNull final @Named("queryDataFetcher") DataFetcher<MetaData> queryDataFetcher,
            @NotNull final @Named("mutationDataFetcher") DataFetcher<MetaData> mutationDataFetcher
    ) {
        return new GraphQLMetaStore(
                Objects.requireNonNull(queryDataFetcher), Objects.requireNonNull(mutationDataFetcher));
    }

    /**
     * Inject queryDataFetcher.
     *
     * @param dataSource  A client object against a SQL database to query metadata from.
     *
     * @return a dataFetcher that queries file metadata from a SQL data store
     *
     * @throws NullPointerException if {@code dataSource} is {@code null}
     */
    @Bean
    public DataFetcher<MetaData> queryDataFetcher(@NotNull final DataSource dataSource) {
        return new SpringSQLQueryDataFetcher(Objects.requireNonNull(dataSource));
    }

    /**
     * Inject mutationDataFetcher.
     *
     * @param dataSource  A client object against a SQL database to save metadata into.
     *
     * @return a dataFetcher that queries file metadata from a SQL data store
     *
     * @throws NullPointerException if {@code dataSource} is {@code null}
     */
    @Bean
    public DataFetcher<MetaData> mutationDataFetcher(@NotNull final DataSource dataSource) {
        return new SpringSQLMutationDataFetcher(Objects.requireNonNull(dataSource));
    }

    /**
     * Inject dataSource.
     *
     * @return a client object against a SQL database
     */
    @Bean
    public DataSource dataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        basicDataSource.setDriverClassName(driverClassName);

        return basicDataSource;
    }
}
