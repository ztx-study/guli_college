package com.zs.guli.service.sms.controller;

import com.alibaba.nacos.client.utils.StringUtils;
import com.aliyuncs.exceptions.ClientException;
import com.zs.guli.common.base.result.R;
import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.FormUtils;
import com.zs.guli.common.base.util.RandomUtils;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ztx-study
 * @date 2020/10/29 9:50
 * @description
 */
@RestController
@RequestMapping("/api/sms")
@Api(value = "短信管理")
//  @CrossOrigin  统一在网关中配置跨域
@Slf4j
@RefreshScope
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("send/{mobile}")
    public R getCode(@PathVariable String mobile) throws ClientException {

        // 校验手机号是否合法
        if(StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)) {
            log.error("请输入正确的手机号码 ");
            throw new GuliException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        // 生成验证码
        String checkCode = RandomUtils.getFourBitRandom();
        // 发送验证码
        smsService.send(mobile, checkCode);
        // 将验证码存入redis缓存
        redisTemplate.opsForValue().set(mobile, checkCode, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}