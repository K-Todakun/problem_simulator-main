package com.example.wsbp.service;



import com.example.wsbp.data.Choice;
import com.example.wsbp.data.Question;
import com.example.wsbp.repository.IChoicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChoicesService implements IChoicesService{

    private IChoicesRepository iChoicesRepository;

    public ChoicesService(IChoicesRepository iChoicesRepository){this.iChoicesRepository = iChoicesRepository;}

    @Override
    public Question question(int unitId, int level){
        int lev = level;
        if (lev == 0) lev++;
        if (lev == 5) lev--;
        var question=iChoicesRepository.question(unitId,lev);
        Random rand = new Random();

        return question.get(rand.nextInt(10));
    }

    @Override
    public List<Choice> choices(int questionId) {


        return iChoicesRepository.choices(questionId);
    }


    @Override
    public Question questions(int questionId) {
        var questions=iChoicesRepository.questions(questionId);
        return questions.get(0);
    }

    @Override
    public Choice choice(int choiceId) {

        var choice = iChoicesRepository.choice(choiceId);


        return choice;
    }

    @Override
    public boolean Correct(int choiceId) {

        var choice = iChoicesRepository.Correct(choiceId);

        if(choice.size()<1){
            return false;
        }

        return true;
    }
}
