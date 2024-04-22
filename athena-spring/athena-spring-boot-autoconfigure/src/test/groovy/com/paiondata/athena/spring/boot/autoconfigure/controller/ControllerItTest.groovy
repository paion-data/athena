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
package com.paiondata.athena.spring.boot.autoconfigure.controller

import com.paiondata.athena.spring.boot.autoconfigure.config.MetaControllerConfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerItTest extends Specification{

    ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MetaControllerConfig.class))

    @Autowired
    FileController fileController

    WebTestClient webTestClient

    def setup() {
        webTestClient = WebTestClient.bindToController(fileController).build()
    }

    def "test upload file"() {
        given:
        contextRunner
                .run {context -> {
                    expectedInitializedBeans.each { it -> context.containsBean(it) }
                    expectedUninitializedBeans.each {  it -> !context.containsBean(it) }
                }}
        ClassPathResource source = new ClassPathResource("/pride-and-prejudice-by-jane-austen.txt")

        when:
        def response  = webTestClient.post()
                .uri("/file/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("files", source))
                .exchange()

        then:
        response.expectStatus().isCreated()
    }
}
