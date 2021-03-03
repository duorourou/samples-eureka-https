package com.github.duorourou.samples.springboot.eureka.httpsserver;

import org.apache.http.conn.ClientConnectionOperator;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerInfo {

    private final ApplicationContext applicationContext;

    public ServerInfo(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping(path = "/info")
    public Environment environment() {
//        eurekaTaskExecutors
        System.out.println(applicationContext.getBean(ClientConnectionOperator.class));
        return applicationContext.getEnvironment();
    }
}
