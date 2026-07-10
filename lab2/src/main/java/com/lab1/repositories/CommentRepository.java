package com.lab1.repositories;
import com.lab1.model.Comment;
public interface CommentRepository {
    void storeComment(Comment comment);
}