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
    List<CustomStatisticsVO> selectStatisticsListOnDeckPaging(Map<String, Object> reqParam);
    int selectStatisticsListOnDeckPagingCount(Map<String, Object> reqParam);
    List<CustomStatisticsVO> selectStatisticsListOnFighterPaging(Map<String, Object> reqParam);
    int selectStatisticsListOnFighterPagingCount(Map<String, Object> reqParam);
    //INSERT
    int insertStatisticsList(Map<String, Object> reqParam);
    //UPDATE
    //DELETE
}
