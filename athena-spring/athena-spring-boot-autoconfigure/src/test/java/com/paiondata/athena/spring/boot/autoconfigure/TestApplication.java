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
package com.paiondata.athena.spring.boot.autoconfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link TestApplication} is used for integration testing Spring Boot wrapping of Athena.
 */
@ComponentScan({"com.paiondata.athena.spring.boot.autoconfigure"})
@SpringBootApplication
public class TestApplication {

    /**
     * Inject test dependencies and start test application for integration tests.
     *
     * @param args  Not used
     */
    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
