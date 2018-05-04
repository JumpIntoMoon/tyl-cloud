package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-05-03 15:39
 **/
@RestController
@RefreshScope
public class HelloController {
    @Value("${server.time}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        return this.hello;
    }

}
