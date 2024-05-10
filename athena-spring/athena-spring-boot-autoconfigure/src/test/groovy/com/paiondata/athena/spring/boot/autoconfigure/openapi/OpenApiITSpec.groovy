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

package com.paiondata.athena.spring.boot.autoconfigure.openapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class SwaggerITSpec extends Specification {

    def setupSpec() {
        System.setProperty("athena__alioss_endpoint_key", "TEST_KEY")
        System.setProperty("athena__table_name", "foo")
    }

    @Autowired
    MockMvc mockMvc

    def "Swagger is enabled"() {
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/swagger-ui/index.html'))

        then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
    }
}
