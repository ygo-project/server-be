package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.UserDAO;
import com.ygo.server.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;


    @Override
    public String getNow() {
        return userDAO.selectNow();
    }
}
