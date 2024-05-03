package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.DeckService;
import com.ygo.server.api.service.vo.DeckVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/deck")
public class DeckController {

    private final DeckService deckService;

    // GET
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Object> list() {
        List<DeckVO> deckList = deckService.getDeckListAll();

        if (deckList != null && !deckList.isEmpty()) {
            return ResponseEntity.ok().body(deckList);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }
    // POST
    // PATCH
    // DELETE
}
