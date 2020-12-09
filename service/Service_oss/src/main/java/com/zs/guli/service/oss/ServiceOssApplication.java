package com.zs.guli.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author optimist
 * @date 2020/9/12 10:52
 * @description
 */
// 取消数据源自动配置，启动时不检查数据库配置信息
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2
@ComponentScan({"com.zs.guli"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }
}
