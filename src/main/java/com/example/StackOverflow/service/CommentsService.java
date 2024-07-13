package com.example.StackOverflow.service;

import com.example.StackOverflow.models.Comments;
import com.example.StackOverflow.models.Questions;

import java.util.HashMap;
import java.util.Map;

public class CommentsService {

    private static CommentsService instance;
    Map<Integer, Comments> commentsLookup;

    private CommentsService() {
        commentsLookup = new HashMap<>();
    }
    public static CommentsService getInstance() {
        if (instance == null) {
            instance = new CommentsService();
        }
        return instance;
    }

    public Comments addComment(String value) {
        Comments comment = new Comments();
        comment.setCommentId(commentsLookup.size()+1);
        comment.setComment(value);
        commentsLookup.put(commentsLookup.size()+1, comment);
        return comment;
    }
}
