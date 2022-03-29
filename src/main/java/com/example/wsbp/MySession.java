package com.example.wsbp;


import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySession extends AbstractAuthenticatedWebSession {

    private String userName;
    private int level;
    private List<Integer> questionIds;
    private List<Integer> answerIds;
    private int unitId;

    public MySession(Request request) {
        super(request);
        this.userName = null;
        this.level = 2;
        questionIds = new ArrayList<>();
        answerIds = new ArrayList<>();
        unitId = 0;
    }

    public void sign(String userName) {
        replaceSession();
        this.userName = userName;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn()) {
            return new Roles(Roles.USER);
        }
        return new Roles();
    }

    @Override
    public boolean isSignedIn() {
        return Objects.nonNull(this.userName);
    }

    public String getUserName() {
        return this.userName;
    }

    public static MySession get() {
        return (MySession) Session.get();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addSolvedQuestion(int questionId, int answerId) {
        questionIds.add(questionId);
        answerIds.add(answerId);
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public List<Integer> getAnswerIds() {
        return answerIds;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
