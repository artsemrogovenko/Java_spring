package com.taskstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TaskStorageApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TaskStorageApplication.class, "--spring.config.import=Y:\\Java_Spring\\hw10\\testing\\task-storage\\src\\main\\resources\\storage-application.yaml");
        SpringApplication.run(TaskStorageApplication.class, args);

    }

}
