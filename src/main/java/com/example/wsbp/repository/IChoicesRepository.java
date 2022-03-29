package com.example.wsbp.repository;



import com.example.wsbp.data.Choice;
import com.example.wsbp.data.Question;

import java.util.List;

public interface IChoicesRepository {

    //単元IDとレベルを指定して問題を取り出す。
    public List<Question> question(int unitId, int level);

    //問題IDから選択肢を取り出すやつ
    public List<Choice> choices(int questionId);

    //正誤判定()
    public List<Choice> Correct(int choiceId);

    //問題IDから問題文と正解の選択肢の文を取り出すやつ
    public List<Question> questions(int questionId);

    //選択肢IDから選択肢の文を取り出すやつ
    public Choice choice(int choiceId);

}
