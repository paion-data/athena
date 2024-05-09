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
import spock.lang.Unroll

class FileControllerConfigSpec extends Specification {

    static final String ALIOSS_ENDPOINT_KEY = "alioss_endpoint_key"
    static final String ALIOSS_ENDPOINT_VALUE = "https://oss-cn-hangzhou.aliyuncs.com"
    static final SystemConfig SYSTEM_CONFIG = SystemConfigFactory.getInstance()
    static final String[] BEAN_WITH_ALIOSS = ["credentialsProvider", "messageDigest", "fileIdGenerator",
                                              "ossClient", "aliOssFileStore"]
    static final String[] BEAN_WITHOUT_ALIOSS = ["messageDigest", "fileIdGenerator"]

    @Unroll
    def 'When Ali OSS #enabled enabled, initialized beans are #expectedInitializedBeans only'() {
        given: "an auto-config class that sources beans"
        ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(FileControllerConfig.class))

        and: "Ali OSS is configured to be ${aliOssEnabled ? "enabled" : "disabled"}"
        if (aliOssEnabled) {
            SYSTEM_CONFIG.setProperty(SYSTEM_CONFIG.getPackageVariableName(ALIOSS_ENDPOINT_KEY), ALIOSS_ENDPOINT_VALUE)
        }

        expect: "required beans are always injected and conditional beans are injected conditionally"
        contextRunner
                .withPropertyValues("athena.spring.alioss.enabled=${aliOssEnabled}")
                .run {context -> {
                    expectedInitializedBeans.each { it -> context.containsBean(it) }
                    expectedUninitializedBeans.each {  it -> !context.containsBean(it) }
                }}

        where:
        aliOssEnabled | expectedInitializedBeans | expectedUninitializedBeans
        true          | BEAN_WITH_ALIOSS         | []
        false         | BEAN_WITHOUT_ALIOSS      | ["credentialsProvider", "ossClient", "aliOssFileStore"]

        enabled = aliOssEnabled ? "is" : "is not"
    }
}
