package com.lab2.lifecycle;

import com.lab2.lifecycle.config.AppConfig;
import com.lab2.lifecycle.managed.ManagedBean;
import com.lab2.lifecycle.singleton.ServiceA;
import com.lab2.lifecycle.prototype.RequestProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // --- PHẦN 1: TEST SINGLETON ---
        System.out.println("--- Before context ---");
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("--- After context  ---\n");

        ctx.getBean(ServiceA.class).doWork();

        // --- PHẦN 2: TEST VÒNG ĐỜI BEAN ---
        System.out.println("\n=== Vong doi Bean ===");
        var bean = ctx.getBean(ManagedBean.class);
        bean.doWork();

        // --- PHẦN 3: CHẠY SAU PHẦN 2 ---
        System.out.println("\n=== Singleton ===");
        var a1 = ctx.getBean(ServiceA.class);
        var a2 = ctx.getBean(ServiceA.class);
        System.out.println("a1 == a2 ? " + (a1 == a2));

        System.out.println("\n=== Prototype ===");
        var p1 = ctx.getBean(RequestProcessor.class);
        var p2 = ctx.getBean(RequestProcessor.class);
        var p3 = ctx.getBean(RequestProcessor.class);

        p1.process("Request A");
        p2.process("Request B");
        p3.process("Request C");

        System.out.println("p1 == p2 ? " + (p1 == p2));
        System.out.println("p1 == p3 ? " + (p1 == p3));

        System.out.println("\n--- Dong context (ctx.close()) ---");
        ctx.close();
    }
}