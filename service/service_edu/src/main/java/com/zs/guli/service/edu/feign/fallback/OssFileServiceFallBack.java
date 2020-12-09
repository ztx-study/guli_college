package com.zs.guli.service.edu.feign.fallback;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author optimist
 * @date 2020/9/18 17:06
 * @description
 */
@Slf4j
@Service
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        return null;
    }

    @Override
    public R removeFile(String url) {
        log.info("讲师阿里云头像删除失败，触发了此熔断异常");
        return R.error();
    }
}
