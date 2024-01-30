package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.UserDAO;
import com.ygo.server.api.service.interfaces.UserService;
import com.ygo.server.api.service.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public boolean isExistID(String id) {
        int userCnt = userDAO.selectUserID(id);
        return userCnt > 0;
    }

    @Override
    public boolean signUp(UserVO reqVo) {
        int insertCnt = userDAO.insertUser(reqVo);
        return insertCnt > 0;
    }
}
