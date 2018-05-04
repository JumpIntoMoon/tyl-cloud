package com.tyl.cloud.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 16:48
 **/
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用服务接口
     *
     * @return
     */
    //@CacheResult//缓存注解 ：@CacheResult @CacheKey @CacheRemove
    @HystrixCommand(fallbackMethod = "listError")
    public String listUsersByRibbon() {
        //注意这里是通过服务名调用而不是服务地址，如果写成服务地址就没法实现客户端负载均衡了
        String result = this.restTemplate.getForObject("http://service-user/listUsers", String.class);
        return result;
    }

    /**
     * 触发熔断时，调用该方法，
     * 对应注解 @HystrixCommand(fallbackMethod = "hiError")
     *
     * @return
     */
    @SuppressWarnings("unused") //禁止警告
    public String listError() {
        return "sorry,error!";
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String name) {
        String result = this.restTemplate.getForObject("http://service-hiClient/hi?name=" + name, String.class);
        return result;
    }

    @SuppressWarnings("unused") //禁止警告
    public String hiError(String name) {
        return "sorry, " + name + " visit  failed ";
    }
}
