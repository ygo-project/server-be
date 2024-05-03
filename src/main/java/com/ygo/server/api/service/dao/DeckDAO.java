package com.ygo.server.api.service.dao;

import com.ygo.server.api.service.vo.DeckVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeckDAO {
    //SELECT
    List<DeckVO> selectDeckListAll();
    //INSERT
    //UPDATE
    //DELETE
}
