package com.example.StackOverflow.service;

import com.example.StackOverflow.exceptions.InvalidInputException;
import com.example.StackOverflow.models.Answers;
import com.example.StackOverflow.models.Comments;
import com.example.StackOverflow.models.Questions;
import com.example.StackOverflow.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerService {
    private static AnswerService instance;
    Map<Integer, Answers> answerLookup;
    UserService userService;
    CommentsService commentsService;

    private AnswerService() {
        answerLookup = new HashMap<>();
        userService = UserService.getInstance();
        commentsService = CommentsService.getInstance();
    }
    public static AnswerService getInstance() {
        if (instance == null) {
            instance = new AnswerService();
        }
        return instance;
    }

    public Answers addAnswer(String value, int questionNumber) {
        Answers answer = new Answers();
        answer.setAnswer(value);
        answer.setAnswerId(answerLookup.size()+1);
        answer.setVote(0);
        answer.setCommentList(new ArrayList<>());
        answer.setQuestionId(questionNumber);
        answerLookup.put(answerLookup.size()+1, answer);
        return answer;
    }

    public void postCommentOnAnswer(String value, int answerNumber, int userId)  throws InvalidInputException {
        if (!answerLookup.containsKey(answerNumber)) {
            throw new InvalidInputException("Enter valid answer number", 400);
        }
        userService.addReputationScore(userId);
        Comments comment = commentsService.addComment(value);
        Answers answer = answerLookup.get(answerNumber);
        List<Comments> commentList = answer.getCommentList();
        commentList.add(comment);
        userService.addCommentToUser(comment, userId);
    }

    public void voteAnswer(int answerNumber, int upOrDownVote, int userId) throws InvalidInputException {
        if (!answerLookup.containsKey(answerNumber)) {
            throw new InvalidInputException("Enter valid answer number", 400);
        }

        if (!(upOrDownVote == 1  || upOrDownVote == -1)) {
            throw new InvalidInputException("Enter valid vote either up or down just once", 400);
        }

        userService.addReputationScore(userId);
        Answers answer = answerLookup.get(answerNumber);
        answer.setVote(answer.getVote() + upOrDownVote);
    }
}
