package com.zs.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.entity.Course;
import com.zs.guli.service.edu.entity.Teacher;
import com.zs.guli.service.edu.entity.vo.TeacherQueryVo;
import com.zs.guli.service.edu.feign.OssFileService;
import com.zs.guli.service.edu.mapper.CourseMapper;
import com.zs.guli.service.edu.mapper.TeacherMapper;
import com.zs.guli.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Teacher> selecePage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {

        // 1、排序，按照sort字段排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        // 2、查询，考虑没有条件的情况，只进行分页查询
        if (teacherQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        // 3、条件查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name", name);
        }
        if (level != null){
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_date",joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_date",joinDateEnd);
        }

        // 本来应该返回TeacherMapper，但是我们继承了ServiceImpl，所以baseMapper就是TeacherMapper
        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectLikeNameByKet(String key) {

        QueryWrapper queryWrapper = new QueryWrapper<Teacher>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name", key);
        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        return list;
    }

    @Override
    public Boolean removeAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if (teacher!=null){
            String avatar  = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)){
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }

        return false;
    }



    @Override
    public Map<String, Object> selectTeacherInfoById(String id) {

        // 获取讲师基本信息
        Teacher teacher = baseMapper.selectById(id);

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", id);
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courseList", courseList);

        return map;
    }

    @Cacheable(value = "index", key = "'selectHotTeacher'")
    @Override
    public List<Teacher> selectHotTeacher() {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}
