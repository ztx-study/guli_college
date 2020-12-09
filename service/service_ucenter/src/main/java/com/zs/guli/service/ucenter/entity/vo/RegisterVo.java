package com.zs.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/10/29 14:19
 * @description
 */
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}