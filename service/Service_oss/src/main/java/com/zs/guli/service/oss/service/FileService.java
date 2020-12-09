package com.zs.guli.service.oss.service;

import java.io.InputStream;

/**
 * @author optimist
 * @date 2020/9/12 15:23
 * @description  文件相关接口
 */
public interface FileService {

    /**
     * 上传文件到阿里云oss
     * @param inputStream  文件输入流
     * @param module  OSS文件夹
     * @param originalFileName  原始文件名字
     * @return
     */
    String uploadFileToOss(InputStream inputStream, String module, String originalFileName);

    /**
     * 阿里云oss文件删除
     * @param url  文件在阿里云oss上的url地址
     */
    void removeFile(String url );
}
