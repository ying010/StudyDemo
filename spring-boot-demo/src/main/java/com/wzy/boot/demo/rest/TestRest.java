package com.wzy.boot.demo.rest;

import com.wzy.boot.demo.service.ITestRestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/test/{id}")
    public String testApi(@PathVariable String id) {
        return testRestService.testApi();
    }

    @GetMapping("/test2/{id}")
    public String testApi2(@PathVariable String id) {
        return testRestService.testApi2();
    }
}
