package com.dispnt.chatroom.controller;

import com.dispnt.chatroom.domain.User;
import com.dispnt.chatroom.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/api/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserDao userDao;
    @CrossOrigin
    @PostMapping(path="/register")
    public @ResponseBody String addUser (@RequestParam String mail, @RequestParam String pwd
            , @RequestParam String nickname) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User u = new User();
        u.setNickname(nickname);
        u.setPassword(pwd);
        u.setMail(mail);
        userDao.save(u);
        return "注册成功！请登录";
    }

    @CrossOrigin
    @PostMapping(path="/login")
    public @ResponseBody
    Object loginUser (@RequestParam String mail, @RequestParam String pwd) {
        User user = userDao.findByMail(mail);
        Map result = new HashMap();
        if(user == null){
            result.put("status", "MailErr");
            return result;
        }
        else {
            if (user.getPassword().equals(pwd)){
                result.put("status", "ok");
                result.put("name", user.getNickname());
                result.put("id", user.getId());
                return result;
            }
        }
        result.put("status", "PwdErr");
        return result;
    }

    @CrossOrigin
    @PostMapping(path="/delete")
    public @ResponseBody
    String delUser (@RequestParam String mail) {
        User user = userDao.findByMail(mail);
        if(user == null){
            return "未找到邮箱用户";
        }
        else {
//            if (user.getPassword().equals(pwd)){
                userDao.delete(user);
                return "删除了"+ user.getNickname();
//            }
        }
//        return "删除失败，请检查密码";
    }



    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userDao.findAll();
    }
}

