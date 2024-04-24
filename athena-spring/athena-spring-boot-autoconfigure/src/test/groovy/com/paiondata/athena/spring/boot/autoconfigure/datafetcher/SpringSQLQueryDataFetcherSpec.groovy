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
package com.paiondata.athena.spring.boot.autoconfigure.datafetcher

import com.paiondata.athena.config.SystemConfig
import com.paiondata.athena.config.SystemConfigFactory
import com.paiondata.athena.metadata.FileType
import com.paiondata.athena.metadata.MetaData

import graphql.schema.DataFetchingEnvironment
import spock.lang.Specification
import spock.lang.Subject

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import javax.sql.DataSource

class SpringSQLQueryDataFetcherSpec extends Specification {
    static final String TABLE_NAME_KEY = "table_name"
    static final String TABLE_NAME_VALUE = "test_table"
    static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance()

    static final String FILE_NAME = "file-test.pdf"
    static final String FILE_TYPE = "PDF"
    static final String UNUSED_FILE_ID = "ajie2nnl97"

    @Subject
    SpringSQLQueryDataFetcher dataFetcher

    DataFetchingEnvironment dataFetchingEnvironment

    @SuppressWarnings('GroovyAccessibility')
    def setup() {
        SYSTEM_CONFIG.setProperty(SYSTEM_CONFIG.getPackageVariableName(TABLE_NAME_KEY), TABLE_NAME_VALUE)

        dataFetchingEnvironment = Mock(DataFetchingEnvironment) {
            getArgument(SpringSQLQueryDataFetcher.FILE_ID) >> UNUSED_FILE_ID
        }
    }

    @SuppressWarnings('GroovyAccessibility')
    def "Happy path meta data fetching retrieves data in two-column format from DB"() {
        setup: "instruct datasource to return a valid one-row two-column data"
        ResultSet resultSet = Mock(ResultSet) {
            next() >> true
            getString(SpringSQLQueryDataFetcher.FILE_NAME_COLUMN) >> FILE_NAME
            getString(SpringSQLQueryDataFetcher.FILE_TYPE_COLUMN) >> FILE_TYPE
        }
        PreparedStatement preparedStatement = Mock(PreparedStatement) {executeQuery() >> resultSet }
        Connection connection = Mock(Connection) {
            prepareStatement(SpringSQLQueryDataFetcher.META_DATA_FETCH_QUERY_TEMPLATE) >> preparedStatement
        }
        dataFetcher = new SpringSQLQueryDataFetcher(
                Mock(DataSource) {
                    getConnection() >> connection
                }
        )

        when: "meta data is being fetched from datasource"
        MetaData metaData = dataFetcher.get(dataFetchingEnvironment)

        then: "meta data attributes got returned in db column results and are used to construct the metadata object"
        metaData.getFileName() == FILE_NAME
        metaData.getFileType() == FileType.PDF

        and: "resources have been released after db query"
        1 * resultSet.close()
        1 * connection.close()
        1 * preparedStatement.close()
    }

    def "When a file ID is not associated with any metadata, runtime exception is thrown"() {
        setup: "instruct datasource to return empty data"
        ResultSet resultSet = Mock(ResultSet) {
            next() >> false
        }
        PreparedStatement preparedStatement = Mock(PreparedStatement) {executeQuery() >> resultSet }
        Connection connection = Mock(Connection) {
            prepareStatement(SpringSQLQueryDataFetcher.META_DATA_FETCH_QUERY_TEMPLATE) >> preparedStatement
        }
        dataFetcher = new SpringSQLQueryDataFetcher(
                Mock(DataSource) {
                    getConnection() >> connection
                }
        )

        when: "meta data is being fetched from datasource"
        dataFetcher.get(dataFetchingEnvironment)

        then: "IllegalStateException is thrown with message"
        Exception exception = thrown(IllegalStateException)
        exception.message == "No meta data found for file ID '${UNUSED_FILE_ID}'"

        and: "resources have been released after db query anyway"
        1 * resultSet.close()
        1 * connection.close()
        1 * preparedStatement.close()
    }
}
