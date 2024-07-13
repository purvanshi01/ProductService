package com.example.StackOverflow.service;

import com.example.StackOverflow.models.Answers;
import com.example.StackOverflow.models.Comments;
import com.example.StackOverflow.models.Questions;
import com.example.StackOverflow.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static UserService instance;
    Map<Integer, User> userLookup;
    Map<String, Integer> userNameToIdMap;

    private UserService() {
        userLookup = new HashMap<>();
        userNameToIdMap = new HashMap<>();
    }
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(String name) {
        User user = new User();
        user.setName(name);
        user.setUserId(userLookup.size()+1);
        user.setAnswersList(new ArrayList<>());
        user.setCommentsList(new ArrayList<>());
        user.setQuestionsList(new ArrayList<>());
        user.setReputationScore(0);
        userLookup.put(userLookup.size()+1, user);
        userNameToIdMap.put(name, user.getUserId());
    }

    public int getUserId(String name) {
        int userId = userNameToIdMap.get(name);
        return userId;
    }

    public void addReputationScore(int userId) {
        User user = userLookup.get(userId);
        user.setReputationScore(user.getReputationScore()+10);
    }

    public void addAnswerToUser(Answers answer, int userId) {
        User user = userLookup.get(userId);
        List<Answers> answersList = user.getAnswersList();
        answersList.add(answer);
    }

    public void addQuestionToUser(Questions question, int userId) {
        User user = userLookup.get(userId);
        List<Questions> questionsList = user.getQuestionsList();
        questionsList.add(question);
    }

    public void addCommentToUser(Comments comment, int userId) {
        User user = userLookup.get(userId);
        List<Comments> commentsList = user.getCommentsList();
        commentsList.add(comment);
    }

    public int getReputationScore(int userId) {
        User user = userLookup.get(userId);
        int score = user.getReputationScore();
        return score;
    }

}
