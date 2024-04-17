/*
 *
 *  * Copyright Paion Data
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.paiondata.athena.spring.boot.autoconfigure.controller;

import static com.paiondata.athena.config.ErrorMessageFormat.INVALID_GRAPHQL_REQUEST;

import com.paiondata.athena.metastore.MetaStore;
import com.paiondata.athena.web.graphql.JsonDocumentParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/metadata/graphql")
public class MetaController {

    private static final Logger LOG = LoggerFactory.getLogger(MetaController.class);

    @Autowired
    @Qualifier("graphQLMetaStore")
    private MetaStore metaStore;

    @Autowired
    private JsonDocumentParser jsonDocumentParser;

    @GetMapping
    public ExecutionResult get(final @NotNull String query) {
        return metaStore.executeNative(Objects.requireNonNull(query));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExecutionResult post(final @NotNull String graphQLDocument) {
        Objects.requireNonNull(graphQLDocument);

        final List<String> requestedMetadataFields = jsonDocumentParser.getFields(graphQLDocument);
        if (requestedMetadataFields.isEmpty()) {
            LOG.error(INVALID_GRAPHQL_REQUEST.logFormat("No metadata field found", graphQLDocument));
            throw new IllegalArgumentException(
                    INVALID_GRAPHQL_REQUEST.format("no metadata field was found", graphQLDocument)
            );
        }

        return metaStore.getMetaData(jsonDocumentParser.getFileId(graphQLDocument), requestedMetadataFields);
    }
}
