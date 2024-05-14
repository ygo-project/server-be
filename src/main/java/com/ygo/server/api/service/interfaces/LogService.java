package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.CustomLeagueVO;
import com.ygo.server.api.service.vo.LogVO;

import java.util.List;
import java.util.Map;

public interface LogService {
    List<LogVO> getNeedChkLogList();
    List<CustomLeagueVO> getRecentChampion();
    int insertLogList(Map<String, Object> reqParam);
    int updateLogListChkYn(Map<String, Object> reqParam);
}
