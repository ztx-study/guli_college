package com.zs.guli.service.statistics.feign;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.statistics.feign.fallback.UcenterMemberServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ztx-study
 * @date 2020/11/22 11:43
 * @description
 */
@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBack.class)
@Repository
public interface UcenterMemberService {

    @GetMapping(value = "/admin/ucenter/member/count-register-num/{day}")
    R countRegisterNum(@PathVariable("day") String day);
}