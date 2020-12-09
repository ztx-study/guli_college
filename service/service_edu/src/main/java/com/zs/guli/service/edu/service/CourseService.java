package com.zs.guli.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zs.guli.service.edu.entity.Course;
import com.zs.guli.service.edu.entity.form.CourseInfoForm;
import com.zs.guli.service.edu.entity.vo.CourseQueryVo;
import com.zs.guli.service.edu.entity.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.guli.service.edu.entity.vo.CoursePublishVo;
import com.zs.guli.service.edu.entity.vo.WebCourseQueryVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    Boolean removeCoverById(String id);

    Boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> selectHotCourse();

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);
}
