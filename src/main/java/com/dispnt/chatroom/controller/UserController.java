package com.dispnt.chatroom.controller;

import com.dispnt.chatroom.User;
import com.dispnt.chatroom.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

@Controller
@RequestMapping(path="/api/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserRepository userRepository;
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
        userRepository.save(u);
        return "注册了。";
    }

    @CrossOrigin
    @PostMapping(path="/delete")
    public @ResponseBody String delUser (@RequestParam String mail, @RequestParam String pwd) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Iterable<User> allusers = getAllUsers();
        for (User i : allusers) {
            if ((i.getMail().equals(mail))&&(i.getPassword().equals(pwd))){
                System.out.println(i.getNickname());
                userRepository.delete(i);
                return "删除了"+i.getNickname();
            }
        }
//        u.setPassword(pwd);
//        u.setMail(mail);
//        userRepository.delete(u);
        return "未删除，密码或账户名不对";
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

