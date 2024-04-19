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

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

import reactor.core.publisher.Flux
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerItTest extends Specification{
    @Shared
    @LocalServerPort
    int port

    WebTestClient webTestClient

    def setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
    }

    def "test upload file"() {
        given:
        def filePath = 'src/test/resources/pride-and-prejudice-by-jane-austen.txt'
        def file = new File(filePath)
        def fileContent = file.bytes

        def factory = new DefaultDataBufferFactory()

        Flux<DataBuffer> dataBufferFlux = Flux.just(factory.wrap(fileContent));

        // 创建一个MultipartBodyBuilder
        MultipartBodyBuilder builder = new MultipartBodyBuilder()
        builder.part("file", dataBufferFlux)
                .filename("pride-and-prejudice-by-jane-austen.txt")
                .contentType(MediaType.TEXT_PLAIN)

        when:
        def response  = webTestClient.post()
                .uri("/file/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()


        then:
        response.expectStatus().isCreated()
    }
}
