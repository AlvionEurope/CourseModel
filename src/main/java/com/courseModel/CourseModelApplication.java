package com.courseModel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.courseModel.*")
@EntityScan("com.courseModel.*")

public class CourseModelApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CourseModelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("http://localhost:8080/swagger-ui.html#");
        System.out.println("http://localhost:8080/h2-console");
    }
}
