package com.zs.guli.service.ucenter.mapper;

import com.zs.guli.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author optimist
 * @since 2020-10-29
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterNumByDay(String day);
}
