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

import com.paiondata.athena.config.SystemConfig
import com.paiondata.athena.config.SystemConfigFactory

import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

import spock.lang.Specification

class MetaControllerConfigSpec extends Specification{

    static final String TABLE_NAME_KEY = "table_name"
    static final String TABLE_NAME_VALUE = "test_table"
    static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance()

    def 'Initialize the required beans'() {
        given:
        SYSTEM_CONFIG.setProperty(SYSTEM_CONFIG.getPackageVariableName(TABLE_NAME_KEY), TABLE_NAME_VALUE)
        ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(MetaControllerConfig.class))

        expect:
        contextRunner
                .run {context -> {
                    expectedInitializedBeans.each { it -> context.containsBean(it) }
                    expectedUninitializedBeans.each {  it -> !context.containsBean(it) }
                }}

        where:
        expectedInitializedBeans                                                       | expectedUninitializedBeans
        ["graphQLMetaStore", "queryDataFetcher", "mutationDataFetcher", "dataSource"]  | []
    }

}
