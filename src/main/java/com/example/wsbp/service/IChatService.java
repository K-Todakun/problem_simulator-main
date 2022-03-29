package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatService {

    public void registerChat(String userName, String body);

    /**
     * ユーザ名とメッセージの一覧を、Chat型のリストで検索する
     * @return Chat型のListインスタンス
     */
    public List<Chat> findChats();
}
