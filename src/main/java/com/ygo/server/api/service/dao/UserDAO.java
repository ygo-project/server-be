package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    int selectUserID(String id);

    int insertUser(UserVO reqVo);
}
