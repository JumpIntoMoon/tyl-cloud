package com.tyl.cloud.feign.feign.imp;

import com.tyl.cloud.feign.feign.ServiceUserAuth;
import org.springframework.stereotype.Component;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-04-28 9:26
 **/
@Component
public class ServiceUserAuthHystrix implements ServiceUserAuth {

    @Override
    public String listUsers() {
        return "sorry, failed ";
    }
}
