package com.dispnt.chatroom.domain;

import javax.persistence.*;

@Entity
public class Usergroup {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="usergroup_id")
    private Integer id;

    @Column(name="usergroup_user_id")
    private Integer user;

    @Column(name="usergroup_group_id")
    private Integer group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getGroupn() {
        return group;
    }

    public void setGroupn(Integer group) {
        this.group = group;
    }

}