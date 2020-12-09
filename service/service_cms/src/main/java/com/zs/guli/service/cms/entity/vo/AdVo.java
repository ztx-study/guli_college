package com.zs.guli.service.cms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/10/23 10:46
 * @description
 */
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;

    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告排序
     */
    private Integer sort;
    /**
     * 广告位类型
     */
    private String type;
}