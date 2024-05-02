package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.FighterDAO;
import com.ygo.server.api.service.interfaces.FighterService;
import com.ygo.server.api.service.vo.FighterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FighterServiceImpl implements FighterService {

    private final FighterDAO fighterDAO;

    @Override
    public boolean insertFighter(FighterVO fighterVO) {
        int insertCnt = fighterDAO.insertFighter(fighterVO);
        return insertCnt > 0;
    }

    @Override
    public boolean updateFighter(FighterVO fighterVO) {
        int updateCnt = fighterDAO.updateFighter(fighterVO);
        return updateCnt > 0;
    }

    @Override
    public List<FighterVO> getFighterByUser(Map<String, Object> paramMap) {
        return fighterDAO.selectFighterListByUser(paramMap);
    }
}
