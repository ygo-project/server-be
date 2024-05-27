package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.CustomStatisticsVO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<CustomStatisticsVO> getStatisticsGroupByNation();
    List<CustomStatisticsVO> getStatisticsGroupByDeck();
    List<CustomStatisticsVO> getStatisticsListOnDeckPaging(Map<String, Object> reqParam);
    int getStatisticsListOnDeckPagingCount(Map<String, Object> reqParam);
    List<CustomStatisticsVO> getStatisticsListOnFighterPaging(Map<String, Object> reqParam);
    int getStatisticsListOnFighterPagingCount(Map<String, Object> reqParam);
    int insertStatisticsList(Map<String, Object> reqParam);
}
