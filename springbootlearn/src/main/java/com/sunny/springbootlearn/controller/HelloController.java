package com.sunny.springbootlearn.controller;

import com.sunny.springbootlearn.properties.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sunny on 2017/11/26.
 */
@RestController
public class HelloController {

    //注入配置文件中的属性
//    @Value("${name}")
//    private String name;
    @Autowired
    private PersonProperties personProperties;

    //@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public String getName(@PathVariable("id") Integer id) {
        return "id:" + id;
    }
}
