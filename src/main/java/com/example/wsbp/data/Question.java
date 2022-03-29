package com.example.wsbp.data;

import java.io.Serializable;


public class Question implements Serializable {
    private final int questionId;
    private final String QuestionStatement;
    private final int unitId;
    private final int level;
    private final int answerId;

    public Question(int questionId, String questionStatement, int unitId, int level, int answerId) {
        this.questionId = questionId;
        this.QuestionStatement = questionStatement;
        this.unitId = unitId;
        this.level = level;
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionStatement() {
        return QuestionStatement;
    }

    public int getUnitId() {
        return unitId;
    }

    public int getLevel() {
        return level;
    }

    public int getAnswerId() {
        return answerId;
    }
}
