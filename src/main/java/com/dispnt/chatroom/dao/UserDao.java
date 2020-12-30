package com.dispnt.chatroom.dao;

import com.dispnt.chatroom.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> { //实体，主键类型
    User findByMail(String mail);
}