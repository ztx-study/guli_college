package com.zs.guli.service.edu.service;

import com.zs.guli.service.edu.entity.Subject;
import com.zs.guli.service.edu.entity.vo.SubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@Service
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);

    List<SubjectVo> nestedList();
}
