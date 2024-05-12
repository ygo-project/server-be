package com.ygo.server.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface StatisticsDAO {
    //SELECT
    //INSERT
    int insertStatisticsList(Map<String, Object> reqParam);
    //UPDATE
    //DELETE
}
