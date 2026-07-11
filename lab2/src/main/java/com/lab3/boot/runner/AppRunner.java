package com.lab3.boot.runner;

import com.lab3.boot.services.NotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final NotificationService notificationService;

    public AppRunner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== App ready — running tasks ===");
        notificationService.send("He thong da khoi dong thanh cong!");
        notificationService.send("Tat ca service san sang.");
        System.out.println("========================\n");
    }
}