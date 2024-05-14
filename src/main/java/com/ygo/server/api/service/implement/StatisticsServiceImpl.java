package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.StatisticsDAO;
import com.ygo.server.api.service.interfaces.StatisticsService;
import com.ygo.server.api.service.vo.CustomStatisticsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsDAO statisticsDAO;

    @Override
    public List<CustomStatisticsVO> getStatisticsGroupByNation() {
        return statisticsDAO.selectStatisticsGroupByNation();
    }

    @Override
    public List<CustomStatisticsVO> getStatisticsGroupByDeck() {
        return statisticsDAO.selectStatisticsGroupByDeck();
    }

    @Override
    public int insertStatisticsList(Map<String, Object> reqParam) {
        return statisticsDAO.insertStatisticsList(reqParam);
    }
}
