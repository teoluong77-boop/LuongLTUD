package com.lab1.proxies;
import com.lab1.model.Comment;
import org.springframework.stereotype.Component;
@Component
public class EmailNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendNotification(Comment comment) {
        System.out.println("  [Email] Notification: " + comment);
    }
}