package com.zs.guli.service.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.guli.service.ucenter.entity.vo.LoginVo;
import com.zs.guli.service.ucenter.entity.vo.RegisterVo;
import com.zs.guli.service.ucenter.entity.Member;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-10-29
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Integer countRegisterNum(String day);
}
