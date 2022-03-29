package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String body) {
        var sql = "insert into chat(user_name, msg_body) values(?, ?)";
        var n = jdbc.update(sql, userName, body);
        return n;
    }

    @Override
    public List<Chat> find() {
        String sql = "select user_name, msg_body from chat";

        List<Chat> chats = jdbc.query(sql, DataClassRowMapper.newInstance(Chat.class));

        return chats;
    }

}
