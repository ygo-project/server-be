package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.StatisticsDAO;
import com.ygo.server.api.service.interfaces.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsDAO statisticsDAO;

    @Override
    public int insertStatisticsList(Map<String, Object> reqParam) {
        return statisticsDAO.insertStatisticsList(reqParam);
    }
}
