package com.zs.guli.service.cms.feign.fallback;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.cms.feign.CmsOssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ztx-study
 * @date 2020/10/23 13:49
 * @description
 */
@Service
@Slf4j
public class CmsOssFileServiceFallBack implements CmsOssFileService {
    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
