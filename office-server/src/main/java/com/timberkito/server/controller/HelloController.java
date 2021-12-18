package com.timberkito.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Timber.Wang
 * @date 2021-12-18 7:46 PM
 */
@RestController
public class HelloController{
    @GetMapping("hello")
    public String hello (){
        return "hello";
    }
}
