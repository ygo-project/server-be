package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDAO {
    //SELECT
    Optional<UserVO> selectUserByID(UserVO reqVo);
    Optional<UserVO> selectUserByIDAndPassword(UserVO reqVo);
    //INSERT
    int insertUser(UserVO reqVo);
    //UPDATE
    //DELETE
}
