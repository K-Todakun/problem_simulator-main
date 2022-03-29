package com.example.wsbp.repository;


import com.example.wsbp.data.Choice;
import com.example.wsbp.data.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoicesRepository implements IChoicesRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public ChoicesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Question> question(int unitId, int level){

        var sql = "select * from question_list "
                + "where unit_id = ? and level = ? ";


        List<Question> question = jdbc.query(sql,
                DataClassRowMapper.newInstance(Question.class),unitId,level);

        System.out.println(question.size());

        return question;


    };

    @Override
    public List<Choice> choices(int questionId){

        var sql = "select * from choices_list "
                + "where question_id = ? ";


        System.out.println("a");
        List<Choice> question = jdbc.query(sql,
                DataClassRowMapper.newInstance(Choice.class),questionId);
        System.out.println("b");
        return question;

    }

    @Override
    public List<Choice> Correct(int choiceId){

        var sql ="select * from question_list "
                + "where question_id = ? ";

        List<Choice> question = jdbc.query(sql,
                DataClassRowMapper.newInstance(Choice.class),choiceId);

        return question;

    }

    @Override
    public List<Question> questions(int questionId){

        var Sql = "select * from question_list "
                + "where question_id = ? ";


        List<Question> question = jdbc.query(Sql,
                DataClassRowMapper.newInstance(Question.class),questionId);

        return question;
    }

    @Override
    public Choice choice(int choicesId) {

        var sql = "select * from choices_list "
                + "where choices_id = ? ";

        List<Choice> question = jdbc.query(sql,
                DataClassRowMapper.newInstance(Choice.class),choicesId);

        return question.get(0);
    }
}
