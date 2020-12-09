package com.zs.guli.service.cms.feign;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.cms.feign.fallback.CmsOssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ztx-study
 * @date 2020/10/23 13:46
 * @description
 */
@Service
@FeignClient(value = "service-oss", fallback = CmsOssFileServiceFallBack.class)
public interface CmsOssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}