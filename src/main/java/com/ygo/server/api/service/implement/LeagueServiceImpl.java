package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.LeagueDAO;
import com.ygo.server.api.service.interfaces.LeagueService;
import com.ygo.server.api.service.vo.LeagueVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueDAO leagueDAO;

    @Override
    public boolean isExistLeague(String name) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);

        return leagueDAO.selectLeagueByName(param).isPresent();
    }

    @Override
    public boolean insertLeague(LeagueVO reqVo) {
        int insertCnt = leagueDAO.insertLeague(reqVo);
        return insertCnt > 0;
    }
}
