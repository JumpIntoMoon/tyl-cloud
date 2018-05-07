package com.tyl.cloud.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 16:49
 **/
@RestController
public class HiController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用服务接口
     *
     * @return
     */
    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    @CacheResult//缓存注解 ：@CacheResult @CacheKey @CacheRemove
    public String sayHi(@RequestParam String name) {
        //注意这里是通过服务名调用而不是服务地址，如果写成服务地址就没法实现客户端负载均衡了
        String result = this.restTemplate.getForObject("http://service-hiClient/hi?name=" + name, String.class);
        return result;
    }

    /**
     * 触发熔断时，调用该方法，
     * 对应注解 @HystrixCommand(fallbackMethod = "hiError")
     *
     * @return
     */
    @SuppressWarnings("unused") //禁止警告
    public String hiError(String name) {
        return "sorry, " + name + " visit  failed ";
    }
}
