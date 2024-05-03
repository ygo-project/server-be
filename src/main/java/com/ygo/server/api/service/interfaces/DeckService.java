package com.ygo.server.api.service.interfaces;

import com.ygo.server.api.service.vo.DeckVO;

import java.util.List;

public interface DeckService {
    List<DeckVO> getDeckListAll();
}
