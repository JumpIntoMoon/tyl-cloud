package com.tyl.cloud.ribbon.controller;

import com.tyl.cloud.ribbon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 16:49
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/listUsersByRibbon")
    public String listUsersByRibbon() {
        return userService.listUsersByRibbon();
    }

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name) {
        return userService.sayHi(name);
    }
}
