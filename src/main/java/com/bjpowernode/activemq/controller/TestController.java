package com.bjpowernode.activemq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class TestController {

    @RequestMapping("/hello")
    public @ResponseBody Object helloWorld(){

        System.out.println(("----=="));

        return "hello world!";
    }

}

