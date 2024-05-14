package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.CustomStatisticsVO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<CustomStatisticsVO> getStatisticsGroupByNation();
    List<CustomStatisticsVO> getStatisticsGroupByDeck();
    int insertStatisticsList(Map<String, Object> reqParam);
}
