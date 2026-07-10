package com.lab1.services;

import com.lab1.model.Comment;
import com.lab1.proxies.CommentNotificationProxy;
import com.lab1.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy notificationProxy;
    public CommentService(@Qualifier("DBCommentRepository") CommentRepository commentRepository,
                          CommentNotificationProxy notificationProxy) {
        this.commentRepository = commentRepository;
        this.notificationProxy  = notificationProxy;
        System.out.println("[INIT] CommentService created!");
    }

    public void publishComment(Comment comment) {
        System.out.println("\n>> Publishing: " + comment);
        commentRepository.storeComment(comment);
        notificationProxy.sendNotification(comment);
        System.out.println(">> Done!\n");
    }
}