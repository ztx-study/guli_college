package com.zs.guli.infrastructure.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ztx-study
 * @date 2020/11/18 10:01
 * @description API网关微服务
 */
// Nacos注解，SpringBoot2.0以后可以不用加。只需引入Nacos依赖和在yml中配置Nacos信息即可
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.zs.guli"})
public class InfrastructureApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApiGatewayApplication.class, args);
    }
}
