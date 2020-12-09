package com.zs.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.entity.Teacher;
import com.zs.guli.service.edu.entity.vo.TeacherQueryVo;
import com.zs.guli.service.edu.feign.OssFileService;
import com.zs.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@Api("讲师管理类")
//  @CrossOrigin  统一在网关中配置跨域
@RestController
@Slf4j
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;


    @ApiOperation(value = "全部讲师列表", notes = "查询所有的讲师列表哦！")
    @GetMapping("/list")
    public R AllTeacherList(){

        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师", notes = "注意：是逻辑删除哦！")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "这是要删除的讲师ID哦！") @PathVariable("id") String id){
        // 删除讲师头像

        Boolean b = teacherService.removeAvatarById(id);

        // 删除讲师
        boolean result = teacherService.removeById(id);
        if (result){
            return R.ok().message("删除成功哦！");
        }else{
            return R.error().message("很抱歉，数据不存在！");
        }

    }
    @ApiOperation(value = "根据ID列表批量删除讲师", notes = "注意：是逻辑删除哦！")
    @DeleteMapping("batchRemove")
    public R removeById(@ApiParam(value = "这是要删除的讲师ID哦！", required = true)
                            @RequestBody List<String> idList){

        boolean result = teacherService.removeByIds(idList);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }

    }
    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam(value = "条件查询对象") TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selecePage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping(value = "add")
    public R addTeacher(@ApiParam(value = "讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        System.out.println(result);
        if (result){
            return R.ok().message("新增成功！");
        }else {
            return R.error().message("很抱歉，讲师新增失败！");
        }
    }

    @ApiOperation(value = "根据ID查询讲师信息")
    @GetMapping(value = "get/{id}")
    public R getTeacherById(@ApiParam(value = "讲师ID") @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("result", teacher);
        }else{
            return R.error().message("对不起，未查询到相关数据！");
        }
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping(value = "update")
    public R updateTeaById(@ApiParam(value = "讲师ID", required = true) @RequestBody Teacher teacher){

        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("数据修改失败！");
        }
    }

    @ApiOperation(value = "根据姓模糊匹配所有复合条件的人")
    @GetMapping(value = "selectLikeNameByKet/{key}")
    public R selectLikeNameByKet(@ApiParam(value = "讲师的姓", required = true)
                                 @PathVariable String key){
        List<Map<String, Object>> nameList = teacherService.selectLikeNameByKet(key);
        return R.ok().data("nameList", nameList);
    }

    @ApiOperation("测试服务调用")
    @GetMapping("test")
    public R test(){
        ossFileService.test();
        return R.ok();
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent(){
        log.info("test_concurrent");
        return R.ok();
    }
    @GetMapping("/message1")
    public String message1() {
        return "message1";
    }

    @GetMapping("/message2")
    public String message2() {
        return "message2";
    }
}

