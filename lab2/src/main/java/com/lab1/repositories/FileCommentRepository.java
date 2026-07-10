package com.lab1.repositories;

import com.lab1.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class FileCommentRepository implements CommentRepository {
    @Override
    public void storeComment(Comment comment) {
        System.out.println("[File] Storing: " + comment);
    }
}