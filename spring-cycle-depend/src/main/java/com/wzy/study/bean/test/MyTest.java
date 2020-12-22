package com.wzy.study.bean.test;

import com.alibaba.fastjson.JSON;
import com.wzy.study.bean.config.MyConfig;
import com.wzy.study.bean.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 王志英
 */
public class MyTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);

        System.err.println(JSON.toJSONString(ac.getBeanDefinitionNames()));

        IndexService iService = ac.getBean(IndexService.class);
        iService.printService();
    }
}
