package com.example.wsbp.data;

import java.io.Serializable;

public class Chat implements Serializable {

    private final String userName;
    private final String msgBody;

    public Chat(String userName, String msgBody) {
        this.userName = userName;
        this.msgBody = msgBody;
    }

    public String getUserName() {
        return userName;
    }

    public String getMsgBody() {
        return msgBody;
    }
}
