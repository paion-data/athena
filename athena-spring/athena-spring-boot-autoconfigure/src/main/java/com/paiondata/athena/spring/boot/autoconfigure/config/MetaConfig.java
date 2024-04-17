/*
 *
 *  * Copyright Paion Data
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.paiondata.athena.spring.boot.autoconfigure.config;

import com.paiondata.athena.example.books.application.SQLMutationDataFetcher;
import com.paiondata.athena.example.books.application.SQLQueryDataFetcher;
import com.paiondata.athena.metadata.MetaData;
import com.paiondata.athena.metastore.MetaStore;
import com.paiondata.athena.metastore.graphql.GraphQLMetaStore;
import com.paiondata.athena.web.graphql.JacksonParser;
import com.paiondata.athena.web.graphql.JsonDocumentParser;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.DataFetcher;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import javax.sql.DataSource;

@Configuration
public class MetaConfig {

    @Value("athena.spring.datasource.username")
    private static String USERNAME;
    @Value("athena.spring.datasource.password")
    private static String PASSWORD;
    @Value("athena.spring.datasource.url")
    private static String URL;
    @Value("athena.spring.datasource.driverclassname")
    private static String DRIVER_CLASS_NAME;

    @Bean
    public JsonDocumentParser jsonDocumentParser() {
        return new JacksonParser();
    }

    @Bean
    public MetaStore graphQLMetaStore(
            @NotNull final @Named("queryDataFetcher") DataFetcher<MetaData> queryDataFetcher,
            @NotNull final @Named("mutationDataFetcher") DataFetcher<MetaData> mutationDataFetcher
    ) {
        return new GraphQLMetaStore(queryDataFetcher, mutationDataFetcher);
    }

    @Bean
    public DataFetcher<MetaData> queryDataFetcher(@NotNull final DataSource dataSource) {
        return new SQLQueryDataFetcher(dataSource);
    }

    @Bean
    public DataFetcher<MetaData> mutationDataFetcher(@NotNull final DataSource dataSource) {
        return new SQLMutationDataFetcher(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(USERNAME);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUrl(URL);
        basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);

        return basicDataSource;
    }
}
