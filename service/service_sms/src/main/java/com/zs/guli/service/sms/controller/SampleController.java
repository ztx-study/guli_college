package com.zs.guli.service.sms.controller;

import com.zs.guli.common.base.result.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztx-study
 * @date 2020/12/8 17:09
 * @description
 */
@RestController
@RequestMapping("/sms/sample")
@RefreshScope
public class SampleController {

    @Value("${aliyun.sms.signName}")
    private String signName;

    @GetMapping("test1")
    public R test1(){
        return R.ok().data("signName", signName);
    }
}
