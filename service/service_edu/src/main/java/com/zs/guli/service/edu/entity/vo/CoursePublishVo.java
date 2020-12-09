package com.zs.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ztx-study
 * @date 2020/10/7 17:01
 * @description
 */
@Data
public class CoursePublishVo  implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String price;//只用于显示
}
