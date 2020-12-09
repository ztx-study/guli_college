package com.zs.guli.service.edu.controller.admin;


import com.zs.guli.common.base.result.R;
import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.ExceptionUtils;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.edu.entity.vo.SubjectVo;
import com.zs.guli.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
//  @CrossOrigin  统一在网关中配置跨域
@Api(value = "课程分类管理")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("import")
    public R batchImport(@RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功！");
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
           throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items", subjectVoList);
    }
}

