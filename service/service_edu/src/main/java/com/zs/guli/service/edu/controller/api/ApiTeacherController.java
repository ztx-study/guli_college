package com.zs.guli.service.edu.controller.api;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.entity.Teacher;
import com.zs.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ztx-study
 * @date 2020/10/22 10:59
 * @description
 */

//@CrossOrigin
@Api("讲师")
@RestController
@RequestMapping(value = "/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表查询")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list).message("讲师列表获取成功！");

    }

    @ApiOperation(value = "获取讲师信息")
    @GetMapping("get/{id}")
    public R get(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id) {
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }


}
