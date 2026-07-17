package com.lab1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.lab1.services",
        "com.lab1.repositories",
        "com.lab1.proxies"
})
public class AppConfig {
}