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

package com.paiondata.athena.spring.boot.autoconfigure.config;

import com.paiondata.athena.file.File;
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.filestore.FileStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class TestFileStore implements FileStore {

    private final Map<String, Object> fileMap = new HashMap<>();

    @Autowired
    private FileIdGenerator fileIdGenerator;

    @Override
    public String upload(final File file) {
        Objects.requireNonNull(file);

        final String fileId = fileIdGenerator.apply(file);
        fileMap.put(fileId, file);

        return fileId;
    }

    @Override
    public InputStream download(final String fileId) {
        Objects.requireNonNull(fileId);

        final File file = (File) fileMap.get(fileId);

        return file.getFileContent();
    }
}