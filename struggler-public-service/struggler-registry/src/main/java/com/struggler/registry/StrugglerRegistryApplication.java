package com.struggler.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class StrugglerRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrugglerRegistryApplication.class, args);
    }

}
