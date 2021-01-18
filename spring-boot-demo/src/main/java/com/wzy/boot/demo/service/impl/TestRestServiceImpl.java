package com.wzy.boot.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzy.boot.demo.mapper.TestMapper;
import com.wzy.boot.demo.service.ITestRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 王志英
 */
@Slf4j
@Service
public class TestRestServiceImpl implements ITestRestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public String testApi() {
        PageHelper.startPage(1, 11);
        log.info("test1-->" + PageHelper.getLocalPage());
        List<Map> mapList = testMapper.selectSalesOrderList();
        log.info("test1-->" + PageHelper.getLocalPage());
        PageInfo pageInfo = new PageInfo(mapList);
        return JSON.toJSONString(mapList);
    }

    @Override
    public String testApi2(Integer id) {
        PageHelper.startPage(1, 11);
        log.info("test2-->" + PageHelper.getLocalPage());
        return JSON.toJSONString(PageHelper.getLocalPage());
    }

    @Override
    public String testApi3(Integer id) {
        log.info("test3-->" + PageHelper.getLocalPage());
        List<Map> mapList = testMapper.selectSalesOrderList();
        log.info("test3-->" + PageHelper.getLocalPage());
        return JSON.toJSONString(JSON.toJSONString(mapList));
    }
}
