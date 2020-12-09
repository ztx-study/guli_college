package com.zs.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/10/8 12:03
 * @description
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}
