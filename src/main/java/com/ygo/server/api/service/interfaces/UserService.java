package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.TokenVO;
import com.ygo.server.api.service.vo.UserVO;

import java.util.Optional;

public interface UserService {
    boolean isExistID(String id);
    UserVO getMatchedUser(UserVO reqVo);
    Optional<UserVO> login(UserVO reqVo);
    boolean signUp(UserVO reqVo);
}
