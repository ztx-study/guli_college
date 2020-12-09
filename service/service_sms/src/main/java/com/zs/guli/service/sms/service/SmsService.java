package com.zs.guli.service.sms.service;


import com.aliyuncs.exceptions.ClientException;

/**
 * @author ztx-study
 * @date 2020/10/29 9:51
 * @description
 */
public interface SmsService {
    void send(String mobile, String checkCode) throws ClientException;
}
