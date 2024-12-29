package com.github.cloudpic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true)
public class CloudPicBackedApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPicBackedApplication.class, args);
        log.info("API校验: http://localhost:8080/api/doc.html");
    }

}
