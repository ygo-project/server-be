package com.ygo.server.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LogDAO {
    //SELECT
    //INSERT
    int insertLogList(Map<String, Object> reqParam);
    //UPDATE
    //DELETE
}
