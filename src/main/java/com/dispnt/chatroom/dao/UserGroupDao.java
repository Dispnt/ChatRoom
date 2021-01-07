package com.dispnt.chatroom.dao;

import com.dispnt.chatroom.domain.Usergroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGroupDao extends CrudRepository<Usergroup, Integer> { //实体，主键类型
    @Query("select u.nickname as nickname, u.id as userid, " +
            "u.mail as mail from User u , Usergroup ug where u.id=ug.user and ug.group = ?1 ")
    List<UserGroupUnionFindUser> FindUserInGroup(Integer id);

    @Query("select q.id as id, q.time as time, q.name as name ," +
            "q.intro as intro from Qun q , Usergroup ug where q.id=ug.group and ug.user = ?1 ")
    List<UserGroupUnionFindGroup> FindGroupByUser(Integer user_id);

    Usergroup findByUserAndGroup(Integer user_id,Integer group_id);
}