package com.zs.guli.service.edu.service.impl;

import com.zs.guli.service.edu.entity.Video;
import com.zs.guli.service.edu.mapper.VideoMapper;
import com.zs.guli.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author optimist
 * @since 2020-08-28
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Override
    public String uploadVideo(InputStream file, String originalFilename) {
        return null;
    }
}
