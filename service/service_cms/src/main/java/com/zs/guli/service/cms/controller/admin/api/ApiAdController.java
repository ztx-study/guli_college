package com.zs.guli.service.cms.controller.admin.api;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.cms.entity.Ad;
import com.zs.guli.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ztx-study
 * @date 2020/10/27 9:41
 * @description
 */
//@CrossOrigin //解决跨域问题
@Api(value = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
public class ApiAdController {

    @Autowired
    private AdService adService;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位id", required = true) @PathVariable String adTypeId) {

        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items", ads);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("save-test")
    public R saveAd(@RequestBody Ad ad){
        //redisTemplate.opsForValue().set("ad1", ad);
        redisTemplate.opsForValue().set("ad", ad);
        return R.ok();
    }

    @GetMapping("get-test/{key}")
    public R getAd(@PathVariable String key){
        Ad ad = (Ad)redisTemplate.opsForValue().get(key);
        return R.ok().data("ad", ad);
    }

    @DeleteMapping("remove-test/{key}")
    public R removeAd(@PathVariable String key){
        Boolean delete = redisTemplate.delete(key);
        System.out.println(delete);//是否删除成功
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println(hasKey);//key是否存在
        return R.ok();
    }
}
