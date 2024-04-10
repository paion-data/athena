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

package com.paiondata.athena.filestore.alioss

import com.aliyun.oss.OSS
import com.aliyun.oss.model.OSSObject
import com.paiondata.athena.file.File
import com.paiondata.athena.file.identifier.FileIdGenerator
import com.paiondata.athena.filestore.FileStore

import spock.lang.Ignore
import spock.lang.Specification

class AliOSSFileStoreSpec extends Specification {

    static final String FILE_ID = "e89f4how89"

    def "Uploading a file delegates its logic to Ali OSS client"() {
        setup: "mock OSS Client"
        OSS ossClient = Mock(OSS.class)

        FileIdGenerator fileIdGenerator = Mock(FileIdGenerator.class)
        fileIdGenerator.apply(_ as File) >> FILE_ID

        FileStore fileStore = new AliOSSFileStore(ossClient, fileIdGenerator)

        File file = Mock(File.class) { getFileContent() >> Mock(InputStream.class) }

        when: "we upload file via FileStore"
        String actualFileId = fileStore.upload(file)

        then: "upload logic delegates to Ali OSS Client and generated file ID is returned"
        1 * ossClient.putObject(_ as String, FILE_ID, _ as InputStream)
        actualFileId == FILE_ID
    }

    @Ignore
    def "Download a file delegates its logic to Ali OSS client"() {
        setup: "mock OSS Client and OSS Object"

        OSSObject object = Mock(OSSObject.class) {getKey() >> FILE_ID }
        object.getObjectContent() >> Mock(InputStream.class)

        OSS ossClient = Mock(OSS.class)
        ossClient.getObject(_ as String, FILE_ID) >> object

        FileIdGenerator fileIdGenerator = Mock(FileIdGenerator.class)

        FileStore fileStore = new AliOSSFileStore(ossClient, fileIdGenerator)

        when: "we download file via FileStore"
        InputStream actualInputStream = fileStore.download(FILE_ID)

        then: "download logic delegates to Ali OSS Client and generated inputStream is returned"
        1 * ossClient.getObject(_ as String, FILE_ID)
    }
}
