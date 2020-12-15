package com.wzy.study.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王志英
 */
@RestController
@RequestMapping("/api")
public class TestApiSwaggerRest {

    @GetMapping("/get")
    public String get() {
        return "hello";
    }
}
