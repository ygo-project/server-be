package com.ygo.server.api.service.implement;

import com.ygo.server.api.service.dao.DeckDAO;
import com.ygo.server.api.service.interfaces.DeckService;
import com.ygo.server.api.service.vo.DeckVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckServiceImpl implements DeckService {

    private final DeckDAO deckDAO;

    @Override
    public List<DeckVO> getDeckListAll() {
        return deckDAO.selectDeckListAll();
    }
}
