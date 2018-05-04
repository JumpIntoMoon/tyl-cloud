package com.tyl.cloud.feign.feign.imp;

import com.tyl.cloud.feign.feign.HiAuth;
import org.springframework.stereotype.Component;

/**
 * @description: description
 * @author: tangYiLong
 * @create: 2018-05-02 18:04
 **/
@Component
public class HiAuthHyStrix implements HiAuth {
    @Override
    public String sayHi(String name) {
        return "sorry, " + name + "visit  failed ";
    }
}
