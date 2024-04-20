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
package com.paiondata.athena.web.endpoints

import com.paiondata.athena.application.ApplicationState
import com.paiondata.athena.application.JerseyTestBinder
import com.paiondata.athena.metadata.FileType
import com.paiondata.athena.metadata.MetaData
import com.paiondata.athena.metastore.MetaStore
import com.paiondata.athena.web.graphql.JsonDocumentParser

import groovy.json.JsonSlurper
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType
import spock.lang.Specification

import java.util.function.BiFunction

class MetaServletSpec extends Specification {

    static final String FILE_ID = "2"
    static final FileType FILE_TYPE = FileType.TXT
    static final String FILE_NAME = "pride-and-prejudice.txt"

    @SuppressWarnings("GroovyAccessibility")
    static final MetaData META_DATA = new MetaData(FILE_NAME, FILE_TYPE)

    JerseyTestBinder jerseyTestBinder

    def setup() {
        ApplicationState applicationState = new ApplicationState()
        applicationState.metadataByFileId = [(FILE_ID): META_DATA]
        applicationState.queryFormatter = new BiFunction<String, List<String>, String>() {
            @Override
            String apply(final String s, final List<String> strings) {
                return """
                    query {
                        metaData(fileId: "$FILE_ID") {
                            $META_DATA.FILE_NAME
                            $META_DATA.FILE_TYPE
                        }
                    }
                """
            }
        }
        applicationState.mutationFormatter = new BiFunction<String, MetaData, String>() {
            @Override
            String apply(final String s, final MetaData metaData) {
                return """
                    mutation createMetaData {
                        createMetaData(fileId: "$FILE_ID", fileName: "$FILE_NAME", fileType: "$FILE_TYPE") {
                            $META_DATA.FILE_NAME
                            $META_DATA.FILE_TYPE
                        }
                    }
                """
            }
        }

        // Create the tet web container to test the resources
        jerseyTestBinder = new JerseyTestBinder(true, applicationState, MetaServlet.class)
    }

    def cleanup() {
        // Release the test web container
        jerseyTestBinder.tearDown()
    }

    def "File meta data can be accessed through GraphQL GET endpoint"() {
        when: "we get meta data via GraphQL GET"
        String actual = jerseyTestBinder.makeRequest(
                "/metadata/graphql",
                [query: URLEncoder.encode("""{metaData(fileId:"$FILE_ID"){fileName\nfileType}}""", "UTF-8")]
        ).get(String.class)

        then: "the response contains all requested metadata info without error"
        new JsonSlurper().parseText(actual) == new JsonSlurper().parseText(expectedMultiFieldMetadataResponse())
    }

    def "File metadata can be accessed through GraphQL POST endpoint"() {
        when: "we get meta data via GraphQL POST"
        String actual = jerseyTestBinder.makeRequest("/metadata/graphql")
                .post(
                        Entity.entity(
                                """
                                {
                                    "query": "{ metaData(fileId: \\"2\\") { fileName fileType } }",
                                    "variables": null
                                }
                                """,
                                MediaType.APPLICATION_JSON
                        )

                )
                .readEntity(String.class)

        then: "the response contains all requested metadata info without error"
        new JsonSlurper().parseText(actual) == new JsonSlurper().parseText(expectedMultiFieldMetadataResponse())
    }

    def "Reading file meta data through POST cannot have field list empty"() {
        given: "a mocked endpoint"
        MetaServlet metaServlet = new MetaServlet(
                Mock(MetaStore),
                Mock(JsonDocumentParser) { getFields(_ as String) >> [] }
        )

        and: "a POST payload that contains empty field list attribute"
        String graphQLDocument = """
                {
                    "query":"{\\n  metaData(fileId:\\"2\\") {\\n      }\\n}",
                    "variables":null
                }
                """

        when: "the payload is sent to the endpoint"
        metaServlet.post(graphQLDocument)

        then: "a runtime error is thrown"
        Exception exception = thrown(IllegalArgumentException)
        exception.message == "Athena could not process the request because no metadata field was found: '$graphQLDocument'"
    }

    def expectedMultiFieldMetadataResponse() {
        """
        {
           "errors":[
              
           ],
           "data":{
              "metaData":{
                 "fileName":"pride-and-prejudice.txt",
                 "fileType":"TXT"
              }
           },
           "extensions":null,
           "dataPresent":true
        }
        """
    }
}
