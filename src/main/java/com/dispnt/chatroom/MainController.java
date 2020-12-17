package com.dispnt.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @CrossOrigin
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String mail, @RequestParam String pwd
            , @RequestParam String nickname) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User u = new User();
        System.out.println(pwd);
        u.setNickname(nickname);
        u.setPassword(pwd);
        u.setMail(mail);
        userRepository.save(u);
        return "Saved";
    }
    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}