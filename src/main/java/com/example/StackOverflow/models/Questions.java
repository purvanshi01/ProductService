package com.example.StackOverflow.models;

import java.util.List;

public class Questions {
    int questionId;
    List<Answers> answerList;
    List<Comments> commentList;

    String question;
    List<String> tags;
    int vote;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<Answers> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answers> answerList) {
        this.answerList = answerList;
    }

    public List<Comments> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comments> commentList) {
        this.commentList = commentList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
