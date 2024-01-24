package com.ygo.server.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    String selectNow();
}
