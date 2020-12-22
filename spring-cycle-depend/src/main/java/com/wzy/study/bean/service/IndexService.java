package com.wzy.study.bean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 王志英
 */
@Component
public class IndexService {
    @Autowired
    UserService userService;

    public IndexService() {
        System.err.println("IndexService create!!");
    }

    public void printService() {
        System.err.println(userService);
    }
}
