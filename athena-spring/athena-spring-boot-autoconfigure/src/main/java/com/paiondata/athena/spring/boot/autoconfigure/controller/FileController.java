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
package com.paiondata.athena.spring.boot.autoconfigure.controller;

import com.paiondata.athena.file.File;
import com.paiondata.athena.filestore.alioss.AliOSSFileStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private AliOSSFileStore aliOSSFileStore;

    @RequestMapping("/upload")
    public String upload(@NotNull final File file) {
        return aliOSSFileStore.upload(Objects.requireNonNull(file));
    }

    @RequestMapping("/download")
    public InputStream download(@NotNull final String fileId) {
        return aliOSSFileStore.download(Objects.requireNonNull(fileId));
    }
}
