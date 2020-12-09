package com.zs.guli.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.guli.service.edu.entity.Teacher;
import com.zs.guli.service.edu.entity.vo.TeacherQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selecePage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo);

    List<Map<String, Object>> selectLikeNameByKet(String key);

    Boolean removeAvatarById(String id);

    Map<String, Object> selectTeacherInfoById(String id);

    List<Teacher> selectHotTeacher();
}
