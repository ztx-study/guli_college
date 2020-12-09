package com.zs.guli.service.oss.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;

/**
 * @author optimist
 * @date 2020/9/12 11:05
 * @description  读取配置文件的OSS信息
 */
@Data
@Component
// 将配置文件里所有以aliyun.oss为前缀的信息读取到类属性里
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;

    private String keyid;
    private  String keysecret;
    // bucket可以使用oss控制台创建，也可以使用java代码创建。记得验证是否已经存在
    private String bucketname;

}
