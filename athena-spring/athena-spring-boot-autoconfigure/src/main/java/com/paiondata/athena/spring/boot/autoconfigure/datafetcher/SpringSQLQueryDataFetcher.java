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
package com.paiondata.athena.spring.boot.autoconfigure.datafetcher;

import static com.paiondata.athena.config.ErrorMessageFormat.META_DATA_NOT_FOUND;

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
import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

/**
 * {@link SpringSQLQueryDataFetcher} fetches file meta data from a SQL data storage via a {@link DataSource}.
 */
public class SpringSQLQueryDataFetcher implements DataFetcher<MetaData> {

    private static final Logger LOG = LoggerFactory.getLogger(SpringSQLQueryDataFetcher.class);
    private static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance();
    private static final String TABLE_NAME_KEY = "table_name";
    private static final String TABLE_NAME = SYSTEM_CONFIG.getStringProperty(
            SYSTEM_CONFIG.getPackageVariableName(TABLE_NAME_KEY)
    ).orElseThrow(() -> {
        LOG.error(ErrorMessageFormat.CONFIG_NOT_FOUND.logFormat(TABLE_NAME_KEY));
        return new IllegalStateException(ErrorMessageFormat.CONFIG_NOT_FOUND.format());
    });

    private static final String FILE_ID = "fileId";
    private static final String FILE_NAME_COLUMN = "file_name";
    private static final String FILE_TYPE_COLUMN = "file_type";
    private static final String META_DATA_FETCH_QUERY_TEMPLATE
            = "SELECT file_name, file_type FROM " + TABLE_NAME + " WHERE file_id = ?";

    private final DataSource dataSource;

    /**
     * Constructor.
     *
     * @param dataSource  a client object against a SQL database to fetch meta data from
     *
     * @throws NullPointerException if {@code dataSource} is {@code null}
     */
    @Inject
    public SpringSQLQueryDataFetcher(@NotNull final DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource);
    }

    @Override
    public MetaData get(final DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        final String fileId = dataFetchingEnvironment.getArgument(FILE_ID);
        final ResultSet resultSet;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(META_DATA_FETCH_QUERY_TEMPLATE)
        ) {
            statement.setString(1, fileId);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                resultSet.close();
                LOG.error(META_DATA_NOT_FOUND.logFormat(fileId));
                throw new IllegalStateException(META_DATA_NOT_FOUND.format(fileId));
            }

            final MetaData metaData = MetaData.of(
                    Stream.of(
                            new AbstractMap.SimpleImmutableEntry<>(
                                    MetaData.FILE_NAME,
                                    resultSet.getString(FILE_NAME_COLUMN)
                            ),
                            new AbstractMap.SimpleImmutableEntry<>(
                                    MetaData.FILE_TYPE,
                                    resultSet.getString(FILE_TYPE_COLUMN)
                            )
                    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );

            resultSet.close();

            return metaData;
        }
    }
}
