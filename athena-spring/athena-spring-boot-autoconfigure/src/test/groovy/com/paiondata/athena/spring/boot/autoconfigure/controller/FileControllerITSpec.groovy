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
package com.paiondata.athena.spring.boot.autoconfigure.controller

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import spock.lang.Specification

@ActiveProfiles("test")
@WebMvcTest(FileController.class)
class FileControllerITSpec extends Specification {

    def setupSpec() {
        System.setProperty("athena__alioss_endpoint_key", "TEST_KEY")
        System.setProperty("athena__table_name", "foo")
    }

    @Autowired
    MockMvc mockMvc

    def "test upload file"() {
        when:
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "pride-and-prejudice-by-jane-austen.txt",
                "text/plain",
                new File("src/test/resources/pride-and-prejudice-by-jane-austen.txt").bytes
        )

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders
                        .multipart("/file/upload")
                        .file(mockMultipartFile)

        then:
        mockMvc.perform(builder).andExpect(status().isCreated())
    }
}
