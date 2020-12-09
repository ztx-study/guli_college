package com.zs.guli.service.ucenter.controller.admin;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztx-study
 * @date 2020/11/20 14:16
 * @description
 */
@Api(value = "会员管理")
//  @CrossOrigin  统一在网关中配置跨域
@RestController
@RequestMapping("/admin/ucenter/member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "根据日期统计注册人数")
    @GetMapping(value = "count-register-num/{day}")
    public R countRegisterNum(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("registerNum", num);
    }
}
