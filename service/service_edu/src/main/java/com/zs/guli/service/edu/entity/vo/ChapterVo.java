package com.zs.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ztx-study
 * @date 2020/10/8 12:04
 * @description
 */
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}
