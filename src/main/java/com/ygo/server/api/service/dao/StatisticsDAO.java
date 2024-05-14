package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.CustomStatisticsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsDAO {
    //SELECT
    List<CustomStatisticsVO> selectStatisticsGroupByNation();
    List<CustomStatisticsVO> selectStatisticsGroupByDeck();
    //INSERT
    int insertStatisticsList(Map<String, Object> reqParam);
    //UPDATE
    //DELETE
}
