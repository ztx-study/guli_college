package com.zs.guli.service.ucenter.service.impl;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.FormUtils;
import com.zs.guli.common.base.util.JwtInfo;
import com.zs.guli.common.base.util.JwtUtils;
import com.zs.guli.common.base.util.MD5;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.ucenter.entity.vo.LoginVo;
import com.zs.guli.service.ucenter.entity.vo.RegisterVo;
import com.zs.guli.service.ucenter.entity.Member;
import com.zs.guli.service.ucenter.mapper.MemberMapper;
import com.zs.guli.service.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author optimist
 * @since 2020-10-29
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 会员注册
     * @param registerVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVo registerVo) {

        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)
                || StringUtils.isEmpty(nickname)) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验验证码
        String checkCode = (String)redisTemplate.opsForValue().get(mobile);
        if(!code.equals(checkCode)){
            throw new GuliException(ResultCodeEnum.CODE_ERROR);
        }

        //是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new GuliException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        //注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        member.setAvatar("https://guli-file-helen.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        baseMapper.insert(member);
    }

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验手机号
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new GuliException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new GuliException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //检验用户是否被禁用
        if(member.getDisabled()){
            throw new GuliException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);

        return jwtToken;
    }

    @Override
    public Integer countRegisterNum(String day) {
        return baseMapper.selectRegisterNumByDay(day);
    }
}
