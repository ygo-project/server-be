package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.LogService;
import com.ygo.server.api.service.interfaces.StatisticsService;
import com.ygo.server.api.service.vo.CustomLeagueVO;
import com.ygo.server.api.service.vo.CustomStatisticsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    private final LogService logService;

    // GET
    @RequestMapping(value = "/nation", method = RequestMethod.GET)
    public ResponseEntity<Object> nation() {

        List<CustomStatisticsVO> nationRate = statisticsService.getStatisticsGroupByNation();

        if (nationRate == null) nationRate = new ArrayList<>();

        return ResponseEntity.ok().body(nationRate);
    }

    @RequestMapping(value = "/deck", method = RequestMethod.GET)
    public ResponseEntity<Object> deck() {

        List<CustomStatisticsVO> deckRate = statisticsService.getStatisticsGroupByDeck();

        if (deckRate == null) deckRate = new ArrayList<>();

        return ResponseEntity.ok().body(deckRate);
    }

    @RequestMapping(value = "/champion", method = RequestMethod.GET)
    public ResponseEntity<Object> champion() {

        List<CustomLeagueVO> championList = logService.getRecentChampion();

        if (championList == null) championList = new ArrayList<>();

        return ResponseEntity.ok().body(championList);
    }
    // POST
    // PATCH
    // DELETE
}
