package com.zs.guli.service.ucenter.entity.vo;

/**
 * @author ztx-study
 * @date 2020/10/30 10:57
 * @description
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;
}
