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
package com.paiondata.athena.spring.boot.autoconfigure.datafetcher;

import com.paiondata.athena.config.ErrorMessageFormat;
import com.paiondata.athena.config.SystemConfig;
import com.paiondata.athena.config.SystemConfigFactory;
import com.paiondata.athena.metadata.MetaData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

/**
 * {@link SpringSQLMutationDataFetcher} saves file meta data into a SQL data storage via a {@link DataSource}.
 */
public class SpringSQLMutationDataFetcher implements DataFetcher<MetaData> {

    private static final Logger LOG = LoggerFactory.getLogger(SpringSQLMutationDataFetcher.class);
    private static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance();
    private static final String TABLE_NAME_KEY = "table_name";
    private static final String TABLE_NAME = SYSTEM_CONFIG.getStringProperty(
            SYSTEM_CONFIG.getPackageVariableName(TABLE_NAME_KEY)
    ).orElseThrow(() -> {
        LOG.error(ErrorMessageFormat.CONFIG_NOT_FOUND.logFormat(TABLE_NAME_KEY));
        return new IllegalStateException(ErrorMessageFormat.CONFIG_NOT_FOUND.format());
    });

    private final DataSource dataSource;

    private static final String FILE_ID = "fileId";

    private static final String META_DATA_PERSIST_QUERY_TEMPLATE =
            "INSERT INTO " + TABLE_NAME + " (file_id, file_name, file_type) VALUES (?, ?, ?)";

    /**
     * Constructor.
     *
     * @param dataSource  a client object against a SQL database to save meta data into
     *
     * @throws NullPointerException if {@code dataSource} is {@code null}
     */
    @Inject
    public SpringSQLMutationDataFetcher(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MetaData get(final DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        final String fileId = dataFetchingEnvironment.getArgument(FILE_ID);
        final String fileName = dataFetchingEnvironment.getArgument(MetaData.FILE_NAME);
        final String fileType = dataFetchingEnvironment.getArgument(MetaData.FILE_TYPE);

        try (
                Connection connection = getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(META_DATA_PERSIST_QUERY_TEMPLATE)
        ) {
            statement.setString(1, fileId);
            statement.setString(2, fileName);
            statement.setString(3, fileType);
            statement.executeUpdate();
        }

        return MetaData.of(
                Stream.of(
                        new AbstractMap.SimpleImmutableEntry<>(MetaData.FILE_NAME, fileName),
                        new AbstractMap.SimpleImmutableEntry<>(MetaData.FILE_TYPE, fileType)
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }

    @NotNull
    private DataSource getDataSource() {
        return dataSource;
    }
}
