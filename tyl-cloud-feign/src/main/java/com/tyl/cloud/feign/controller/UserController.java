package com.tyl.cloud.feign.controller;

import com.tyl.cloud.feign.feign.ServiceUserAuth;
import com.tyl.cloud.feign.feign.imp.HiAuthHyStrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 18:39
 **/
@RestController
public class UserController {
    @Autowired
    ServiceUserAuth serviceUserAuth;

    @Autowired
    HiAuthHyStrix hiAuthHyStrix;

    @GetMapping("/listUsersByFeign")
    public String ListUsers() {
        String users = serviceUserAuth.listUsers();
        return users;
    }

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name) {
        String users = hiAuthHyStrix.sayHi(name);
        return users;
    }
}
