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
package com.paiondata.athena.example.books.web.endpoints

import com.paiondata.athena.application.JerseyTestBinder
import com.paiondata.athena.config.SystemConfig
import com.paiondata.athena.config.SystemConfigFactory
import com.paiondata.athena.example.books.application.SQLDBResourceManager

import spock.lang.Specification

abstract class AbstractServletSpec extends Specification {

    static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance()

    JerseyTestBinder jerseyTestBinder

    def childSetup() {
        // intentionally left blank
    }

    def setup() {
        SYSTEM_CONFIG.setProperty('athena__data_source_provider', 'com.paiondata.athena.example.books.application.BooksBinderFactory$DerbyDataSourceProvider')
        SQLDBResourceManager.migrateDatabase()
        childSetup()
    }
}
