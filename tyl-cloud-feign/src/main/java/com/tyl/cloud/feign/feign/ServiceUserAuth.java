package com.tyl.cloud.feign.feign;

import com.tyl.cloud.feign.feign.imp.ServiceUserAuthHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-27 18:37
 **/
@Component
@FeignClient(name = "service-user", fallback = ServiceUserAuthHystrix.class)
public interface ServiceUserAuth {
    @GetMapping("/listUsers")
    String listUsers();
}
