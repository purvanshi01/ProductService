package com.example.StackOverflow;

import com.example.StackOverflow.models.Questions;
import com.example.StackOverflow.service.AnswerService;
import com.example.StackOverflow.service.QuestionService;
import com.example.StackOverflow.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class StackOverflowApplication {
    public static void main(String[] args) {
        try {
            UserService userService = UserService.getInstance();
            QuestionService questionService = QuestionService.getInstance();
            AnswerService answerService = AnswerService.getInstance();
            userService.addUser("Purvanshi");
            userService.addUser("Naman");
            userService.addUser("Karun");

            List<String> tags = new ArrayList<>();
            tags.add("java");
            tags.add("oops");
            questionService.postQuestion("What are oops concepts in terms of java", tags, userService.getUserId("Purvanshi"));
            questionService.postCommentOnQuestion("explain in detail please", 1, userService.getUserId("Naman"));
            questionService.postCommentOnQuestion("like what are solid principles and all", 1, userService.getUserId("Purvanshi"));
            questionService.voteQuestion(1, 1, userService.getUserId("Naman"));
            questionService.postAnswerOnQuestion("ok solid stands for single responsibility etc", 1, userService.getUserId("Naman"));
            answerService.postCommentOnAnswer("what is single responsibility", 1, userService.getUserId("Purvanshi"));


            answerService.voteAnswer(1, 1, userService.getUserId("Karun"));
            answerService.voteAnswer(1, 1, userService.getUserId("Purvanshi"));

            int purvanshiReputationScore = userService.getReputationScore(userService.getUserId("Purvanshi"));
            System.out.println("Purvanshi's reputation score is " + purvanshiReputationScore);
            int namanReputationScore = userService.getReputationScore(userService.getUserId("Naman"));
            System.out.println("Naman's reputation score is " + namanReputationScore);
            int karunReputationScore = userService.getReputationScore(userService.getUserId("Karun"));
            System.out.println("Karun's reputation score is " + karunReputationScore);

            List<Questions> questionsList = questionService.searchQuestionsBasedOnTag("java");
            for (Questions question : questionsList) {
                System.out.println(question.getQuestion());
            }

            List<Questions> questionsListForUserProfile = questionService.searchQuestionsBasedOnUserProfiles(userService.getUserId("Purvanshi"));
            for (Questions question : questionsListForUserProfile) {
                System.out.println(question.getQuestion());
            }

            List<Questions> questionsListBasedOnKeyword = questionService.searchQuestionsBasedOnKeyword("oops");
            for (Questions question : questionsListBasedOnKeyword) {
                System.out.println(question.getQuestion());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
