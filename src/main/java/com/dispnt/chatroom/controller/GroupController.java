package com.dispnt.chatroom.controller;

import com.dispnt.chatroom.dao.GroupDao;
import com.dispnt.chatroom.domain.Qun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path="/api/group") // This means URL's start with /demo (after Application path)
public class GroupController {
    @Autowired
    private GroupDao groupDao;
    @CrossOrigin
    @PostMapping(path="/add")
    public @ResponseBody String addMessage (@RequestParam String intro,
                                            @RequestParam String name)
    {
        Qun g = new Qun();
        g.setIntro(intro);
        g.setName(name);
        g.setTime(new Date());
        groupDao.save(g);
        return "添加成功";
    }

//    @CrossOrigin
//    @PostMapping(path="/get")
//    public @ResponseBody
//    List getMessage (@RequestParam Integer group_id) {
//        List<MessageUnionInfo> user =  groupDao.findMassageInfo(group_id, Sort.by("time"));
//        return user;
//    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Qun> getAllGroups() {
        return groupDao.findAll();
    }
}

