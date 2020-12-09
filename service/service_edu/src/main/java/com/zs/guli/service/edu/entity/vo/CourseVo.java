package com.zs.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/9/28 9:14
 * @description
 */
@Data
public class CourseVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}
