package com.lab1.repositories;

import com.lab1.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class DBCommentRepository implements CommentRepository {
    @Override
    public void storeComment(Comment comment) {
        System.out.println("  [DB] Storing: " + comment);
    }
}