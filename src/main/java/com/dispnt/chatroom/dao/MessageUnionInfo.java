package com.dispnt.chatroom.dao;

import java.util.Date;

public interface MessageUnionInfo {
    Integer getUser();

    String getContent();

    Date getTime();

    String getNickname();
}
