package com.zs.guli.service.edu.controller.api;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.entity.Course;
import com.zs.guli.service.edu.entity.vo.WebCourseQueryVo;
import com.zs.guli.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ztx-study
 * @date 2020/10/27 15:01
 * @description
 */
//@CrossOrigin
@Api(value = "课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("课程列表")
    @GetMapping("list")
    public R list(
            @ApiParam(value = "查询对象", required = false)
                    WebCourseQueryVo webCourseQueryVo){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return  R.ok().data("courseList", courseList);
    }
}
