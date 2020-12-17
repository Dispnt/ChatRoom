package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer student_id;

    private String student_name;

    private String student_sex;

    public Integer getstudentId() {
        return student_id;
    }

    public void setId(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return student_name;
    }

    public void setName(String student_name) {
        this.student_name = student_name;
    }

    public String getSex() {
        return student_sex;
    }

    public void setSex(String student_sex) {
        this.student_sex = student_sex;
    }
}