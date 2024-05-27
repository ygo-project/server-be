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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/search/deck", method = RequestMethod.GET)
    public ResponseEntity<Object> searchDeck(@RequestParam(value = "startDate") String startDate,
                                             @RequestParam(value = "endDate") String endDate,
                                             @RequestParam(value = "deckSeq") String deckSeq,
                                             @RequestParam(value = "size") int size,
                                             @RequestParam(value = "page") int page) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("deckSeq", deckSeq);

        paramMap.put("limit", size);
        paramMap.put("offset", size * (page - 1));
        List<CustomStatisticsVO> statisticsList = statisticsService.getStatisticsListOnDeckPaging(paramMap);

        if (statisticsList == null) statisticsList = new ArrayList<>();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCnt", statisticsService.getStatisticsListOnDeckPagingCount(paramMap));
        resultMap.put("data", statisticsList);

        return ResponseEntity.ok().body(resultMap);
    }

    @RequestMapping(value = "/search/fighter", method = RequestMethod.GET)
    public ResponseEntity<Object> searchFighter(@RequestParam(value = "startDate") String startDate,
                                                @RequestParam(value = "endDate") String endDate,
                                                @RequestParam(value = "fighterSeq") String fighterSeq,
                                                @RequestParam(value = "size") int size,
                                                @RequestParam(value = "page") int page) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("fighterSeq", fighterSeq);

        paramMap.put("limit", size);
        paramMap.put("offset", size * (page - 1));
        List<CustomStatisticsVO> statisticsList = statisticsService.getStatisticsListOnFighterPaging(paramMap);

        if (statisticsList == null) statisticsList = new ArrayList<>();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCnt", statisticsService.getStatisticsListOnFighterPagingCount(paramMap));
        resultMap.put("data", statisticsList);

        return ResponseEntity.ok().body(resultMap);
    }
    // POST
    // PATCH
    // DELETE
}
