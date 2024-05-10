package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.LogDAO;
import com.ygo.server.api.service.interfaces.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogDAO logDAO;

    @Override
    public int insertLogList(Map<String, Object> reqParam) {
        return logDAO.insertLogList(reqParam);
    }
}
