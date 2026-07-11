package com.lab3.boot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
// Tương đương viết tay 3 annotation:
//   @Configuration          <- day la configuration class
//   @ComponentScan          <- quet tat ca package con tu day
//   @EnableAutoConfiguration <- Spring Boot tu cau hinh theo classpath
public class Lab3Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }
}
