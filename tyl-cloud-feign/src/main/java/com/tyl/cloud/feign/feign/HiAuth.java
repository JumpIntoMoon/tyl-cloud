package com.tyl.cloud.feign.feign;

import com.tyl.cloud.feign.feign.imp.HiAuthHyStrix;
import com.tyl.cloud.feign.feign.imp.ServiceUserAuthHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-05-02 18:03
 **/
@Component
@FeignClient(name = "service-hiClient", fallback = HiAuthHyStrix.class)
public interface HiAuth {
    @GetMapping("/hi")
    String sayHi(@RequestParam String name);
}
