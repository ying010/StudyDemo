package com.wzy.boot.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 王志英
 */
@Mapper
public interface TestMapper {

    List<Map> selectSalesOrderList();

    List<Map> selectSalesOrderList2(@Param("id") Integer id);
}
