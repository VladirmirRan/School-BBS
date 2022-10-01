package com.school.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.school")
public class SchoolBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolBbsApplication.class, args);
    }

}
