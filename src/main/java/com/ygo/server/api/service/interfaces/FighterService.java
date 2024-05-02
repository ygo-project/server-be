package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.FighterVO;

import java.util.List;
import java.util.Map;

public interface FighterService {
    boolean insertFighter(FighterVO fighterVO);
    boolean updateFighter(FighterVO fighterVO);
    List<FighterVO> getFighterByUser(Map<String, Object> paramMap);
}
