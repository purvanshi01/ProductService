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

public class QuestionService {

    private static QuestionService instance;
    Map<Integer, Questions> questionsLookup;
    UserService userService;
    CommentsService commentsService;
    AnswerService answerService;

    private QuestionService() {
        questionsLookup = new HashMap<>();
        userService = UserService.getInstance();
        commentsService = CommentsService.getInstance();
        answerService = AnswerService.getInstance();
    }
    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }

        return instance;
    }

    public void postQuestion(String value, List<String> tags, int userId) {
        userService.addReputationScore(userId);
        Questions question = new Questions();
        question.setQuestion(value);
        question.setAnswerList(new ArrayList<>());
        question.setQuestionId(questionsLookup.size()+1);
        question.setTags(tags);
        question.setCommentList(new ArrayList<>());
        question.setVote(0);
        userService.addQuestionToUser(question, userId);
        questionsLookup.put(questionsLookup.size()+1, question);
    }

    public void voteQuestion(int questionNumber, int upOrDownVote, int userId) throws InvalidInputException {
        if (!questionsLookup.containsKey(questionNumber)) {
            throw new InvalidInputException("Enter valid question number", 400);
        }

        if (!(upOrDownVote == 1  || upOrDownVote == -1)) {
            throw new InvalidInputException("Enter valid vote either up or down just once", 400);
        }

        userService.addReputationScore(userId);
        Questions question = questionsLookup.get(questionNumber);
        question.setVote(question.getVote() + upOrDownVote);
    }

    public void postCommentOnQuestion(String value, int questionNumber, int userId)  throws InvalidInputException {
        if (!questionsLookup.containsKey(questionNumber)) {
            throw new InvalidInputException("Enter valid question number", 400);
        }
        userService.addReputationScore(userId);
        Comments comment = commentsService.addComment(value);
        Questions question = questionsLookup.get(questionNumber);
        List<Comments> commentList = question.getCommentList();
        commentList.add(comment);
        userService.addCommentToUser(comment, userId);
    }

    public void postAnswerOnQuestion(String value, int questionNumber, int userId) throws InvalidInputException {
        if (!questionsLookup.containsKey(questionNumber)) {
            throw new InvalidInputException("Enter valid question number", 400);
        }
        userService.addReputationScore(userId);
        Answers answer = answerService.addAnswer(value, questionNumber);
        Questions question = questionsLookup.get(questionNumber);
        List<Answers> answersList = question.getAnswerList();
        answersList.add(answer);
        userService.addAnswerToUser(answer, userId);
    }

    public List<Questions> searchQuestionsBasedOnTag (String tag) {
        List<Questions> questionsList = new ArrayList<>();
        for (int questionNumber : questionsLookup.keySet()) {
            Questions question = questionsLookup.get(questionNumber);
            List<String> tags = question.getTags();

            for (String availableTag : tags) {
                if (availableTag.equals(tag)) {
                    questionsList.add(question);
                }
            }
        }
        return questionsList;
    }

    public List<Questions> searchQuestionsBasedOnUserProfiles (int userId) {
        User user = userService.userLookup.get(userId);
        List<Questions> questionsList = user.getQuestionsList();
        return questionsList;
    }

    public List<Questions> searchQuestionsBasedOnKeyword (String word) {
        List<Questions> questionsList = new ArrayList<>();
        for (int questionNumber : questionsLookup.keySet()) {
            Questions question = questionsLookup.get(questionNumber);
            String questionValue = question.getQuestion();
            boolean contains = questionValue.contains(word);
            if (contains)
                questionsList.add(question);
        }
        return questionsList;
    }
}
