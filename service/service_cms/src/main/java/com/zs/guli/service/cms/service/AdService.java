package com.zs.guli.service.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zs.guli.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.guli.service.cms.entity.vo.AdVo;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author optimist
 * @since 2020-10-22
 */
public interface AdService extends IService<Ad> {

    IPage<AdVo> selectPage(Long page, Long limit);

    Boolean removeAdImageById(String id);

    List<Ad> selectByAdTypeId(String adTypeId);
}
