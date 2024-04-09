package com.paiondata.athena.filestore.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.paiondata.athena.file.File;
import com.paiondata.athena.file.identifier.FileIdGenerator;
import com.paiondata.athena.filestore.FileStore;

import jakarta.validation.constraints.NotNull;

import java.io.InputStream;
import java.util.Objects;

public class OSSFileStore implements FileStore {

    // 存储空间名称
    public static final String DEFAULT_BUCKET = "default-bucket";

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // 访问凭证
    private final EnvironmentVariableCredentialsProvider credentialsProvider;
    // OSS 的 Java 客户端
    private OSS ossClient;

    private final FileIdGenerator fileIdGenerator;

    public OSSFileStore(@NotNull final FileIdGenerator fileIdGenerator) throws com.aliyuncs.exceptions.ClientException {
        // 从环境变量中获取访问凭证，需要先确保已设置环境变量 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET
        this.credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        this.fileIdGenerator = fileIdGenerator;

        createOSSClient();
        createBucket();
    }

    @Override
    public String upload(final File file) {
        Objects.requireNonNull(file);
        String fileId = getFileIdGenerator().apply(file);

        ossClient.putObject(DEFAULT_BUCKET, fileId, file.getFileContent());

        try {
            ossClient.putObject(DEFAULT_BUCKET, fileId, file.getFileContent());
        } catch (OSSException exception) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + exception.getErrorMessage());
            System.out.println("Error Code:" + exception.getErrorCode());
            System.out.println("Request ID:" + exception.getRequestId());
            System.out.println("Host ID:" + exception.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return fileId;
    }

    @Override
    public InputStream download(final String fileId) {
        OSSObject ossObject = null;

        try {
            // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元数据
            ossObject = ossClient.getObject(DEFAULT_BUCKET, fileId);
            // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
            return ossObject.getObjectContent();
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return null;
    }

    // 创建 OSSClient 实例
    @NotNull
    private void createOSSClient() {
        this.ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
    }

    // 创建存储空间
    @NotNull
    private void createBucket() {
        final Bucket bucket = ossClient.createBucket(DEFAULT_BUCKET);
    }

    @NotNull
    private FileIdGenerator getFileIdGenerator() {
        return this.fileIdGenerator;
    }
}
