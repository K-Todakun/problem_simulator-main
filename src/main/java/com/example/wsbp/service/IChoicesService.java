package com.example.wsbp.service;



import com.example.wsbp.data.Choice;
import com.example.wsbp.data.Question;

import java.util.List;

public interface IChoicesService {

    public Question question(int unitId, int level);

    public List<Choice> choices(int questionId);

    public Question questions(int questionId);

    public Choice choice(int questionId);

    public boolean Correct(int choiceId);

}
