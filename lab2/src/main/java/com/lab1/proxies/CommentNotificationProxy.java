package com.lab1.proxies;
import com.lab1.model.Comment;
public interface CommentNotificationProxy {
    void sendNotification(Comment comment);
}