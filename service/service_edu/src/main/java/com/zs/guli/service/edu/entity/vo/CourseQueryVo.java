package com.zs.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/9/28 9:11
 * @description
 */
@Data
public class CourseQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
