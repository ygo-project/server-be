package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.CustomLeagueVO;
import com.ygo.server.api.service.vo.LogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogDAO {
    //SELECT
    List<LogVO> selectNeedChkLogList();
    List<CustomLeagueVO> selectRecentChampion();
    //INSERT
    int insertLogList(Map<String, Object> reqParam);
    //UPDATE
    int updateLogListChkYn(Map<String, Object> reqParam);
    //DELETE
}
