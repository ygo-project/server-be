package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.LeagueVO;

public interface LeagueService {
    boolean isExistLeague(String name);

    boolean insertLeague(LeagueVO reqVo);
}
