package com.zs.guli.service.sms.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ztx-study
 * @date 2020/10/29 9:39
 * @description
 */
@Data
@Component
//注意prefix要写到最后一个 "." 符号之前
@ConfigurationProperties(prefix="aliyun.sms")
public class SmsProperties {
    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;
}
