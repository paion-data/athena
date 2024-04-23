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
package com.paiondata.athena.spring.boot.autoconfigure

import com.paiondata.athena.spring.boot.autoconfigure.config.TestConfig
import com.paiondata.athena.spring.boot.autoconfigure.controller.FileController
import com.paiondata.athena.spring.boot.autoconfigure.database.SQLDBResourceManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
class ControllerItTest extends Specification {

    @Autowired
    FileController fileController

    WebTestClient webTestClient

    def setup() {

        SQLDBResourceManager.migrateDatabase()

        webTestClient = WebTestClient.bindToController(fileController).build()
    }

    def "test upload file"() {
        given:

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
