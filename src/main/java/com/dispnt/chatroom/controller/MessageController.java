package com.dispnt.chatroom.controller;

import com.dispnt.chatroom.dao.MessageDao;
import com.dispnt.chatroom.unioninfo.MessageUnionFindUser;
import com.dispnt.chatroom.domain.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path="/api/chat") // This means URL's start with /demo (after Application path)
public class MessageController {
    @Autowired
    private MessageDao messageDao;
    @CrossOrigin
    @PostMapping(path="/add")
    public @ResponseBody String addMessage (@RequestParam Integer group_id, @RequestParam Integer user_id
            , @RequestParam String msg_content) {
        Messages m = new Messages();
        m.setGroup(group_id);
        m.setUser(user_id);
        m.setContent(msg_content);
        m.setTime(new Date());
        messageDao.save(m);
        return "发送成功";
    }

    @CrossOrigin
    @PostMapping(path="/get")
    public @ResponseBody
    List getMessage (@RequestParam Integer group_id) {
//        System.out.println("开始获取");
//        List<Messages> messages = messageDao.findByGroup(group_id);
//        //[{"id":1,"content":"1群成员1消息","time":"2020-12-30T03:21:49.000+00:00","group_id":1,"user_id":1}
//        // ,{"id":3,"content":"1群成员2消息","time":"2020-12-30T03:28:21.000+00:00","group_id":1,"user_id":2}]
//        for(Messages msg : messages){
//            System.out.println(msg.getUser());
//            User user = userDao.findByMail("yuyuanjiajian@gmail.com");
//            System.out.println(user);
//        }
//        return messages;
        List<MessageUnionFindUser> user =  messageDao.findMassageInfo(group_id, Sort.by("time"));
        return user;
    }

//
//    @CrossOrigin
//    @PostMapping(path="/delete")
//    public @ResponseBody
//    String delUser (@RequestParam String mail, @RequestParam String pwd) {
//        User user = userDao.findByMail(mail);
//        if(user == null){
//            return "未找到邮箱用户";
//        }
//        else {
//            if (user.getPassword().equals(pwd)){
//                userDao.delete(user);
//                return "删除了"+ user.getNickname();
//            }
//        }
//        return "删除失败，请检查密码";
//    }
//
//
//
    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Messages> getAllMessages() {
        return messageDao.findAll();
    }
}

