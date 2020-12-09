package com.zs.guli.service.edu.entity.excel;

        import com.alibaba.excel.annotation.ExcelProperty;
        import lombok.Data;

/**
 * @author ztx-study
 * @date 2020/9/22 14:36
 * @description 课程分类Excel表格对应实体类
 */
@Data
public class SubjectExcel {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
