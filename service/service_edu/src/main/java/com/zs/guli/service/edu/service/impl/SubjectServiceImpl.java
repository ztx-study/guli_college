package com.zs.guli.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zs.guli.service.edu.entity.Subject;
import com.zs.guli.service.edu.entity.excel.SubjectExcel;
import com.zs.guli.service.edu.entity.vo.SubjectVo;
import com.zs.guli.service.edu.listener.SubjectExcelListener;
import com.zs.guli.service.edu.mapper.SubjectMapper;
import com.zs.guli.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void batchImport(InputStream inputStream) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, SubjectExcel.class, new SubjectExcelListener(subjectMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet()
                .doRead();
    }

    @Override
    public List<SubjectVo> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
