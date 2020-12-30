package com.dispnt.chatroom.domain;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="msg_id")
    private Integer id;

    @Column(name="msg_group_id")
    private Integer group;

    @Column(name="msg_user_id", unique = true, nullable = false)
    private Integer user;

    @Column(name="msg_content")
    private String content;

    @Column(name="msg_time")
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group;
    }

    public void setGroup(Integer group_id) {
        this.group = group_id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user_id) {
        this.user = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
