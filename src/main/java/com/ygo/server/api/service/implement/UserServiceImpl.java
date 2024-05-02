package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.UserDAO;
import com.ygo.server.api.service.interfaces.UserService;
import com.ygo.server.api.service.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public boolean isExistID(String id) {
        return userDAO.selectUserByID(UserVO.builder().userId(id).build()).isPresent();
    }

    @Override
    public UserVO getMatchedUser(UserVO reqVo) {
        Optional<UserVO> selected = userDAO.selectUserByIDAndPassword(reqVo);
        return selected.orElse(null);
    }

    @Override
    public UserVO getMatchedUserById(UserVO reqVo) {
        Optional<UserVO> selected = userDAO.selectUserByID(reqVo);
        return selected.orElse(null);
    }

    @Override
    public Optional<UserVO> login(UserVO reqVo) {
        return userDAO.selectUserByID(reqVo);
    }

    @Override
    public boolean signUp(UserVO reqVo) {
        int insertCnt = userDAO.insertUser(reqVo);
        return insertCnt > 0;
    }
}
