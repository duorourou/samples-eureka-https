package com.github.duorourou.samples.springbooteurekaclientproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient(autoRegister = true)
@SpringBootApplication
public class SpringbootEurekaClientProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEurekaClientProductApplication.class, args);
    }

}
