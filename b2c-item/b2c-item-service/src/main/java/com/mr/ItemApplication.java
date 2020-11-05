package com.mr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Package: com.mr
 * @Description:
 * @Author: xph
 * @CreateDate: 2020/10/22 11:52
 * @UpdateDate: 2020/10/22 11:52
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mr.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
