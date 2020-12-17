package com.example.accessingdatamysql;

import javax.persistence.*;

@Entity
public class Student {
    @Id        // 标识实体类主键
    private String studentId;

    @Column(name="student_name")
    private String name;

    @Column(name="student_sex")
    private String sex;


    public String getstudentId() {
        return studentId;
    }

    public void setstudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}



//@Entity // This tells Hibernate to make a table out of this class
//public class Student {
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private String student_id;
//
//    private String student_name;
//
//    private String student_sex;
//
//    public String getstudentId() {
//        return student_id;
//    }
//
//    public void setId(String student_id) {
//        this.student_id = student_id;
//    }
//
//    public String getName() {
//        return student_name;
//    }
//
//    public void setName(String student_name) {
//        this.student_name = student_name;
//    }
//
//    public String getSex() {
//        return student_sex;
//    }
//
//    public void setSex(String student_sex) {
//        this.student_sex = student_sex;
//    }
//}