package com.zs.guli.service.statistics.feign.fallback;

import com.zs.guli.common.base.result.R;
import com.zs.guli.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ztx-study
 * @date 2020/11/22 11:44
 * @description
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public R countRegisterNum(String day) {
        //错误日志
        log.error("熔断器被执行");
        return R.ok().data("registerNum", 0);
    }
}