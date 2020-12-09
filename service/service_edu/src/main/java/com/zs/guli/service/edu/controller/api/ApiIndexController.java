package com.zs.guli.service.edu.controller.api;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.entity.Course;
import com.zs.guli.service.edu.entity.Teacher;
import com.zs.guli.service.edu.service.CourseService;
import com.zs.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ztx-study
 * @date 2020/10/27 11:19
 * @description
 */

//@CrossOrigin
@Api(value="首页")
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("课程列表")
    @GetMapping
    public R index(){

        // 获取首页最热门前8条课程数据
        List<Course> courseList = courseService.selectHotCourse();
        // 获取首页推荐前4条讲师数据
        List<Teacher> teacherList = teacherService.selectHotTeacher();

        return R.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
