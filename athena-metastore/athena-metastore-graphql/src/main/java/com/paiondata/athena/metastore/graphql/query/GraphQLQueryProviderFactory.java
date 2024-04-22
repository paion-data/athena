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
package com.paiondata.athena.metastore.graphql.query;

import jakarta.validation.constraints.NotNull;
import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

/**
 * {@link GraphQLQueryProviderFactory} is a design pattern that abstracts away from
 * {@link com.paiondata.athena.metastore.MetaStore} the {@link GraphQLQueryProvider} implementation choices.
 */
@Immutable
@ThreadSafe
public final class GraphQLQueryProviderFactory {

    /**
     * Constructor.
     * <p>
     * Suppress default constructor for noninstantiability.
     *
     * @throws AssertionError when called
     */
    private GraphQLQueryProviderFactory() {
        throw new AssertionError();
    }

    /**
     * Returns a runtime object that implements {@link GraphQLQueryProvider} specification.
     *
     * @return the same instance
     */
    @NotNull
    public static GraphQLQueryProvider getInstance() {
        return TemplateBasedGraphQLQueryProvider.getInstance();
    }
}
