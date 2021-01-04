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
        PageHelper.startPage(1, 10);
        List<Map> mapList = testMapper.selectSalesOrderList();
        PageInfo pageInfo = new PageInfo(mapList);
        return JSON.toJSONString(pageInfo);
    }
}
