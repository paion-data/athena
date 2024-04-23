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

package com.paiondata.athena.spring.boot.autoconfigure.database

import org.apache.commons.dbcp2.BasicDataSource

import jakarta.inject.Provider
import jakarta.validation.constraints.NotNull

import javax.sql.DataSource

class DerbyDataSourceProvider implements Provider<DataSource> {
    private static final DataSource DATA_SOURCE = initDataSource();

    @NotNull
    private static DataSource initDataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        basicDataSource.setUrl("jdbc:derby:memory:Athena;create=true");
        return basicDataSource;
    }

    @Override
    DataSource get() {
        return DATA_SOURCE;
    }
}
