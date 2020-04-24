package com.struggler.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 系统管理
 *
 * @author wanglp
 * @date 2019/6/8
 **/
@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
public class StrugglerSysManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrugglerSysManageApplication.class, args);
    }

}
