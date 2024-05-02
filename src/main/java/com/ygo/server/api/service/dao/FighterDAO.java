package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.FighterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FighterDAO {
    //SELECT
    List<FighterVO> selectFighterListByUser(Map<String, Object> paramMap);
    //INSERT
    int insertFighter(FighterVO fighterVO);
    //UPDATE
    int updateFighter(FighterVO fighterVO);
    //DELETE
}
