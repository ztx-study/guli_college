package com.zs.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.zs.guli.service.oss.service.FileService;
import com.zs.guli.service.oss.utils.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author optimist
 * @date 2020/9/12 15:26
 * @description
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;


    @Override
    public String uploadFileToOss(InputStream inputStream, String module, String originalFileName) {
        // 读取oss配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }

        // 开始组装objectName（文件路径/文件名）
        String dateFolder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileExtensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = module + "/" +dateFolder + "/" + fileName  + fileExtensionName;
        System.out.println("组装的objectName为："+objectName);


        // 上传文件流。
        ossClient.putObject(bucketname, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        // 返回文件的url地址
        String fileOssUrl = "https://" + bucketname +"." + endpoint + "/" + objectName;
        return fileOssUrl;
    }

    @Override
    public void removeFile(String url) {
        // 读取oss配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }


        String host = "https://" + bucketname +"." + endpoint+"/";
        String objectName = url.substring(host.length());

        // 删除单个文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketname, objectName);


        // 关闭OSSClient。
        ossClient.shutdown();



    }
}
