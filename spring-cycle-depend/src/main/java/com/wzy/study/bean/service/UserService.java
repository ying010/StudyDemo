package com.wzy.study.bean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 王志英
 */
@Component
public class UserService {

    @Autowired
    IndexService indexService;

    public UserService() {
        System.err.println("UserService create!!");
    }
}
