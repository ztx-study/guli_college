package com.zs.guli.service.edu.service;

import com.zs.guli.service.edu.entity.Chapter;
import com.zs.guli.service.edu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */

public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
