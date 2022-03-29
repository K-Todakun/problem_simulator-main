package com.example.wsbp.data;

import java.io.Serializable;

public class Choice implements Serializable {
    private final int choicesId;
    private final int questionId;
    private final String choicesStatement;

    public Choice(int choicesId, int questionId, String choicesStatement) {
        this.choicesId = choicesId;
        this.questionId = questionId;
        this.choicesStatement = choicesStatement;
    }

    public int getChoicesId() {
        return choicesId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getChoicesStatement() {
        return choicesStatement;
    }
}
