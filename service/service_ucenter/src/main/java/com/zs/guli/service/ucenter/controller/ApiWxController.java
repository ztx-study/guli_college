package com.zs.guli.service.ucenter.controller;

import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.ExceptionUtils;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.ucenter.util.UcenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author ztx-study
 * @date 2020/11/2 14:11
 * @description
 */
//  @CrossOrigin  统一在网关中配置跨域
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
@Slf4j
public class ApiWxController {
    @Autowired
    private UcenterProperties ucenterProperties;

    @GetMapping("login")
    public String genQrConnect(HttpSession session){

        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //处理回调url
        String redirecturi = "";
        try {
            redirecturi = URLEncoder.encode(ucenterProperties.getRedirectUri(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.URL_ENCODE_ERROR);
        }

        //处理state：生成随机数，存入session
        String state = UUID.randomUUID().toString();
        log.info("生成 state = " + state);
        session.setAttribute("wx_open_state", state);

        String qrcodeUrl = String.format(
                baseUrl,
                ucenterProperties.getAppId(),
                redirecturi,
                state
        );

        return "redirect:" + qrcodeUrl;
    }
}
