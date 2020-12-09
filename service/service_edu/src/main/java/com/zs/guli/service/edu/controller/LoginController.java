package com.zs.guli.service.edu.controller;

import com.zs.guli.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author optimist
 * @date 2020/9/7 14:16
 * @description  用户登录
 */
//  @CrossOrigin  统一在网关中配置跨域
@RestController
@RequestMapping("/user")
public class LoginController {


    @PostMapping("/login")
    public R userLogin(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R getUserInfo(){
        return R.ok().data("name","admin")
                     .data("roles","[admin]")
                     .data("avatar","https://img.alicdn.com/tfs/TB1Sv5PV9zqK1RjSZPcXXbTepXa-80-80.png");
    }


}
