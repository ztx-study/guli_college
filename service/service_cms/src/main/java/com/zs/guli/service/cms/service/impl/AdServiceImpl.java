package com.zs.guli.service.cms.service.impl;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.guli.common.base.result.R;
import com.zs.guli.service.cms.entity.Ad;
import com.zs.guli.service.cms.entity.vo.AdVo;
import com.zs.guli.service.cms.feign.CmsOssFileService;
import com.zs.guli.service.cms.mapper.AdMapper;
import com.zs.guli.service.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author optimist
 * @since 2020-10-22
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Autowired
    private CmsOssFileService cmsOssFileService;

    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {

        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVo> pageParam = new Page<>(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public Boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmpty(imagesUrl)){
                //删除图片
                R r = cmsOssFileService.removeFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }

    // 该方法支持缓存
    @Cacheable(value = "index", key = "'selectByAdTypeId'")
    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {

        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort", "id");
        queryWrapper.eq("type_id", adTypeId);
        return baseMapper.selectList(queryWrapper);
    }
}
