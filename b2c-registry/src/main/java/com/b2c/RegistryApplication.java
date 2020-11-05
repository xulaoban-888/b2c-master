package com.b2c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: com.mr.common
 * @Description:
 * @Author: xph
 * @CreateDate: 2020/10/22 11:35
 * @UpdateDate: 2020/10/22 11:35
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }
}
