package com.tyl.cloud.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 16:08
 **/
@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }
}
