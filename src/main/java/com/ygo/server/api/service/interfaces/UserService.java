package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.UserVO;

public interface UserService {
    boolean isExistID(String id);
    boolean signUp(UserVO reqVo);
}
