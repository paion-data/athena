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
package com.paiondata.athena.spring.boot.autoconfigure.datafetcher

import com.paiondata.athena.config.SystemConfig
import com.paiondata.athena.config.SystemConfigFactory
import com.paiondata.athena.metadata.MetaData

import graphql.schema.DataFetchingEnvironment
import spock.lang.Specification
import spock.lang.Subject

import java.sql.Connection
import java.sql.PreparedStatement

import javax.sql.DataSource

class SpringSQLMutationDataFetcherSpec extends Specification {
    static final String TABLE_NAME_KEY = "table_name"
    static final String TABLE_NAME_VALUE = "test_table"
    static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance()

    static final String FILE_NAME = "file-test.pdf"
    static final String FILE_TYPE = "PDF"
    static final String FILE_ID = "ajie2nnl97"

    @Subject
    SpringSQLMutationDataFetcher dataFetcher

    DataFetchingEnvironment dataFetchingEnvironment

    @SuppressWarnings('GroovyAccessibility')
    def setup() {
        SYSTEM_CONFIG.setProperty(SYSTEM_CONFIG.getPackageVariableName(TABLE_NAME_KEY), TABLE_NAME_VALUE)

        dataFetchingEnvironment = Mock(DataFetchingEnvironment) {
            getArgument(SpringSQLMutationDataFetcher.FILE_ID) >> FILE_ID
            getArgument(MetaData.FILE_NAME) >> FILE_NAME
            getArgument(MetaData.FILE_TYPE) >> FILE_TYPE
        }
    }

    @SuppressWarnings('GroovyAccessibility')
    def "Happy path meta data persistence causes DataSource to execute save query"() {
        setup: "instruct data source to fake a save setup"

        PreparedStatement preparedStatement = Mock(PreparedStatement)
        Connection connection = Mock(Connection) {
            prepareStatement(SpringSQLMutationDataFetcher.META_DATA_PERSIST_QUERY_TEMPLATE) >> preparedStatement
        }
        dataFetcher = new SpringSQLMutationDataFetcher(
                Mock(DataSource) {
                    getConnection() >> connection
                }
        )

        when: "meta data is being saved"
        dataFetcher.get(dataFetchingEnvironment)

        then: "a SQL save query is sent"
        1 * preparedStatement.executeUpdate()
    }
}
