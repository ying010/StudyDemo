package com.wzy.study.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王志英
 */
@RestController
@RequestMapping("/boot")
public class BootApiSwaggerRest {

    @GetMapping("/get")
    public String get() {
        return "hello";
    }
}
