package com.zs.guli.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zs.guli.service.edu.entity.Subject;
import com.zs.guli.service.edu.entity.excel.SubjectExcel;
import com.zs.guli.service.edu.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ztx-study
 * @date 2020/9/22 14:40
 * @description  课程表格处理监听器
 */
@AllArgsConstructor // 全参构造函数
@NoArgsConstructor // 无参构造函数
@Slf4j
public class SubjectExcelListener extends AnalysisEventListener<SubjectExcel> {


    private SubjectMapper subjectMapper;

//    public SubjectExcelListener(SubjectMapper subjectMapper) {
//        this.subjectMapper = subjectMapper;
//    }
//    public SubjectExcelListener() {
//    }

    @Override
    public void invoke(SubjectExcel data, AnalysisContext analysisContext) {
        log.info("解析到一条记录: {}", data);
        // 处理读取出来的数据
        String levelOneTitle = data.getLevelOneTitle();
        String levelTwoTitle = data.getLevelTwoTitle();
        log.info("levelOneTitle: {}", levelOneTitle);
        log.info("levelTwoTitle: {}", levelTwoTitle);

        // 组装数据，存入数据库
        //判断一级分类是否重复
        Subject subjectLevelOne = this.getByTitle(levelOneTitle);
        String parentId = null;
        if(subjectLevelOne == null) {
            //将一级分类存入数据库
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);//一级分类名称
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else{
            parentId = subjectLevelOne.getId();
        }

        //判断二级分类是否重复
        Subject subjectLevelTwo = this.getSubByTitle(levelTwoTitle, parentId);
        if(subjectLevelTwo == null){
            //将二级分类存入数据库
            Subject subject = new Subject();
            subject.setTitle(levelTwoTitle);
            subject.setParentId(parentId);
            subjectMapper.insert(subject);//添加
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        log.info("所有数据解析完成！");
    }
    private Subject getByTitle(String title) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");//一级分类
        return subjectMapper.selectOne(queryWrapper);
    }

    /**
     * 根据分类名称和父id查询这个二级分类是否存在
     * @param title
     * @return
     */
    private Subject getSubByTitle(String title, String parentId) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}
