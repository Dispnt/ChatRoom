package com.dispnt.chatroom.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Qun {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="group_id")
    private Integer id;


    @Column(name="group_create_time")
    private Date time;

    @Column(name="group_intro")
    private String intro;

    @Column(name="group_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
