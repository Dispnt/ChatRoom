package com.dispnt.chatroom.dao;

import com.dispnt.chatroom.domain.Messages;
import com.dispnt.chatroom.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections



public interface MessageDao extends CrudRepository<Messages, Integer> {
//    @Query("select m.content,m.time from Messages m where m.group = ?1")
    List<Messages> findByGroup(Integer group_id);
    @Query("select u.nickname as nickname, u.id as user, m.content as content , " +
            "m.time as time from User u , Messages m where u.id=m.user  and  m.group = ?1 ")
    List<MessageUnionInfo> findMassageInfo(Integer id);
}