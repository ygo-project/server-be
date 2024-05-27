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
    public List<CustomStatisticsVO> getStatisticsListOnDeckPaging(Map<String, Object> reqParam) {
        return statisticsDAO.selectStatisticsListOnDeckPaging(reqParam);
    }

    @Override
    public int getStatisticsListOnDeckPagingCount(Map<String, Object> reqParam) {
        return statisticsDAO.selectStatisticsListOnDeckPagingCount(reqParam);
    }

    @Override
    public List<CustomStatisticsVO> getStatisticsListOnFighterPaging(Map<String, Object> reqParam) {
        return statisticsDAO.selectStatisticsListOnFighterPaging(reqParam);
    }

    @Override
    public int getStatisticsListOnFighterPagingCount(Map<String, Object> reqParam) {
        return statisticsDAO.selectStatisticsListOnFighterPagingCount(reqParam);
    }

    @Override
    public int insertStatisticsList(Map<String, Object> reqParam) {
        return statisticsDAO.insertStatisticsList(reqParam);
    }
}
