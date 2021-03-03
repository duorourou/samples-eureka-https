package com.github.duorourou.samples.springboot.eureka.httpsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootEurekaHttpsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEurekaHttpsServerApplication.class, args);
    }

}
