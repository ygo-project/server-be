package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.LeagueVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface LeagueDAO {
    //SELECT
    Optional<LeagueVO> selectLeagueByName(Map<String, Object> paramMap);
    //INSERT
    int insertLeague(LeagueVO reqVo);
    //UPDATE
    //DELETE
}
