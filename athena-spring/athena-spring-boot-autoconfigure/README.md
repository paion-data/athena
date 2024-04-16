Athena Spring Boot Autoconfigure
================================

The Athena spring boot autoconfigure package provides the core code needed to use Athena with Spring Boot 3. It includes:

1. BeanConfig

2. FileController

BeanConfig
----------

| Core Bean         | Description|
|-------------------|-------------------------------------------------------------------------------|
| `fileIdGenerator` | Generator of the unique identification of the file|
| `ossClient`       | Ali OSS Java client for managing OSS resources such as storage space and files|
| `aliOssFileStore` | Upload files to and download files from Ali OSS|

FileController
--------------

FileController will receive and process requests to upload and download files.
It includes:

1. upload: Receives a request with a com.paiondata.athena.file.File object as an argument to upload the file to Ali OSS and generate a FileId as a unique identifier for the file to return

2. download: Receives a request with FileId as the parameter, and returns the InputStream corresponding to FileId
