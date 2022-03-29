package com.example.wsbp.data;

public class Result {
    private final String questionStatement;
    private final String choiceStatement;
    private final String answerStatement;

    public Result(String questionStatement, String choiceStatement, String answerStatement) {
        this.questionStatement = questionStatement;
        this.choiceStatement = choiceStatement;
        this.answerStatement = answerStatement;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public String getChoiceStatement() {
        return choiceStatement;
    }

    public String getAnswerStatement() {
        return answerStatement;
    }
}
