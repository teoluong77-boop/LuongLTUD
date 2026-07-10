package com.lab1;
import com.lab1.config.AppConfig;
import com.lab1.model.Comment;
import com.lab1.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Khoi tao Spring Context ===");
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var service = context.getBean(CommentService.class);
        service.publishComment(new Comment("Alice", "Spring DI hoat dong!"));
        var service2 = context.getBean(CommentService.class);
        System.out.println("Cung instance? " + (service == service2));
        context.close();
    }
}