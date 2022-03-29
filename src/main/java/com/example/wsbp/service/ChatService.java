package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {

    private IChatRepository chatRepository;

    @Autowired
    public ChatService(IChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void registerChat(String userName, String body) {
        int n = chatRepository.insert(userName, body);
        System.out.println("記録行数:" + n);
    }

    @Override
    public List<Chat> findChats() {
        var chats = chatRepository.find();
        System.out.println("データ件数:" + chats.size());
        return chats;
    }
}
