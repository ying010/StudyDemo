package com.wzy.boot.demo.rest;

import com.alibaba.fastjson.JSON;
import com.wzy.boot.demo.service.ITestRestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王志英
 */
@RestController
@RequestMapping("/test")
public class TestRest {

    @Resource
    ITestRestService testRestService;

    @GetMapping("/test")
    public String testApi() {
        return testRestService.testApi();
    }
}
