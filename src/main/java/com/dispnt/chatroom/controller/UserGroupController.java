package com.dispnt.chatroom.controller;

import com.dispnt.chatroom.dao.*;
import com.dispnt.chatroom.domain.Usergroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/api/usergroup") // This means URL's start with /demo (after Application path)
public class UserGroupController {
    @Autowired
    private UserGroupDao usergroupDao;

    @CrossOrigin
    @PostMapping(path="/get/user")
    public @ResponseBody
    List<UserGroupUnionFindUser> getUserInGroup (@RequestParam Integer group_id) {
        List<UserGroupUnionFindUser> usergroup =  usergroupDao.FindUserInGroup(group_id);
        return usergroup;
    }

    @CrossOrigin
    @PostMapping(path="/get/group")
    public @ResponseBody
    List<UserGroupUnionFindGroup> getGroupbyUser (@RequestParam Integer user_id) {
        List<UserGroupUnionFindGroup> usergroup2 =  usergroupDao.FindGroupByUser(user_id);
        return usergroup2;
    }

    @CrossOrigin
    @PostMapping(path="/remove")
    public @ResponseBody
    String removeUserInGroup (@RequestParam Integer user_id,@RequestParam Integer group_id) {
        Usergroup usergroup = usergroupDao.findByUserAndGroup(user_id,group_id);
        usergroupDao.delete(usergroup);
        return "在"+usergroup.getGroupn()+"群删除了"+ usergroup.getUser();
    }

    @CrossOrigin
    @PostMapping(path="/add")
    public @ResponseBody
    String addUserInGroup (@RequestParam Integer user_id,@RequestParam Integer group_id) {
        Usergroup u = new Usergroup();
        u.setUser(user_id);
        u.setGroupn(group_id);
        try {
            usergroupDao.save(u);
        } catch (Exception e){
            return "失败！请检查ID是否正确,或者已经添加";
        }

        return "在"+u.getGroupn()+"群添加了"+ u.getUser();
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Usergroup> getAllUsers() {
        return usergroupDao.findAll();
    }
}

