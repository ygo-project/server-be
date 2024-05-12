package com.ygo.server.api.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsVO {
    private Date regDate;
    private Long fighterSeq;
    private Long deckSeq;
    private Integer win;
    private Integer lose;

    @Override
    public String toString() {
        return "StatisticsVO{" +
                "regDate=" + regDate +
                ", fighterSeq=" + fighterSeq +
                ", deckSeq=" + deckSeq +
                ", win=" + win +
                ", lose=" + lose +
                '}';
    }
}
