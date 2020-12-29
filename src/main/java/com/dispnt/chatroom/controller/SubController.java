package com.dispnt.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/") // This means URL's start with /demo (after Application path)
public class SubController {
    @CrossOrigin
    @GetMapping(path="/test")
    public @ResponseBody String getTestContent() {
        return "NMSL11?";
    }
}

