package com.zs.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ztx-study
 * @date 2020/8/28 14:41
 * @description
 */
@SpringBootApplication
@ComponentScan({"com.zs.guli"})
@EnableDiscoveryClient   // 注册到Nacos
@EnableFeignClients  // 远程服务调用
@RefreshScope
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }
}
