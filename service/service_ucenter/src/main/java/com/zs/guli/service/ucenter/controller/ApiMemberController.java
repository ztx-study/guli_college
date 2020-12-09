package com.zs.guli.service.ucenter.controller;


import com.zs.guli.common.base.result.R;
import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.JwtInfo;
import com.zs.guli.common.base.util.JwtUtils;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.ucenter.entity.vo.LoginVo;
import com.zs.guli.service.ucenter.entity.vo.RegisterVo;
import com.zs.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author optimist
 * @since 2020-10-29
 */
@Api(value = "会员管理")
//  @CrossOrigin  统一在网关中配置跨域
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request){
    
        try{
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo", jwtInfo);
        }catch (Exception e){
            log.error("解析用户信息失败，" + e.getMessage());
            throw new GuliException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }
}

