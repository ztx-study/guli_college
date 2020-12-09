package com.zs.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.guli.service.edu.entity.Video;

import java.io.InputStream;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
public interface VideoService extends IService<Video> {
    String uploadVideo(InputStream file, String originalFilename);

}
