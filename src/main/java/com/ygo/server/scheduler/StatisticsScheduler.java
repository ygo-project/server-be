package com.ygo.server.scheduler;

import com.ygo.server.api.service.interfaces.LogService;
import com.ygo.server.api.service.interfaces.StatisticsService;
import com.ygo.server.api.service.vo.LogVO;
import com.ygo.server.api.service.vo.StatisticsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.*;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class StatisticsScheduler {

    private final LogService logService;

    private final StatisticsService statisticsService;

    @Scheduled(cron = "0 0/1 * * * *") // 1분마다
    public void logToStatistics() {
        log.info("scheduler [logToStatistics] -- start");

        List<LogVO> logList = logService.getNeedChkLogList();

        if (logList != null && !logList.isEmpty()) {
            Map<StatisticsKey, int[]> map = new HashMap<>();
            for (LogVO vo : logList) {
                StatisticsKey key = new StatisticsKey(vo.getRegDate(), vo.getFighterSeq(), vo.getDeckSeq());

                if (!map.containsKey(key)) map.put(key, new int[]{ 0, 0 });

                if (vo.getResult().equals("1")) {
                    map.get(key)[0]++;
                } else if (vo.getResult().equals("0")) {
                    map.get(key)[1]++;
                }
            }

            List<StatisticsVO> statisticsList = new ArrayList<>();
            for (StatisticsKey key : map.keySet()) {
                statisticsList.add(StatisticsVO.builder()
                        .regDate(key.reg_date)
                        .fighterSeq(key.fighter_seq)
                        .deckSeq(key.deck_seq)
                        .win(map.get(key)[0])
                        .lose(map.get(key)[1]).build());
            }

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("logList", logList);
            int doneCnt = logService.updateLogListChkYn(paramMap);

            paramMap.clear();
            paramMap.put("statisticsList", statisticsList);
            int insertCnt = statisticsService.insertStatisticsList(paramMap);

            log.info("scheduler [logToStatistics] -- " + doneCnt + "건 통계 처리 완료 : " + insertCnt + " 행 입력 완료");
        } else {
            log.info("scheduler [logToStatistics] -- nodata");
        }

        log.info("scheduler [logToStatistics] -- end");
    }

    private class StatisticsKey {
        private final Date reg_date;
        private final Long fighter_seq;
        private final Long deck_seq;

        public StatisticsKey(Date date, Long fighter, Long deck) {
            reg_date = date;
            fighter_seq = fighter;
            deck_seq = deck;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StatisticsKey that = (StatisticsKey) o;
            return Objects.equals(reg_date, that.reg_date) && Objects.equals(fighter_seq, that.fighter_seq) && Objects.equals(deck_seq, that.deck_seq);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reg_date, fighter_seq, deck_seq);
        }
    }
}
