package com.dispnt.chatroom.dao;

import com.dispnt.chatroom.domain.Qun;
import org.springframework.data.repository.CrudRepository;


public interface GroupDao extends CrudRepository<Qun, Integer> {

}