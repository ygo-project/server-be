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
public class LogVO {
    Long seq;
    Date regDate;
    Long leagueSeq;
    Long fighterSeq;
    Long deckSeq;
    Long opponentFighterSeq;
    Long opponentDeckSeq;
    String round;
    String result;
    String chkYn;

    public boolean isValid() {
        if (leagueSeq == null) return false;
        if (fighterSeq == null) return false;
        if (deckSeq == null) return false;
        if (opponentFighterSeq == null) return false;
        if (opponentDeckSeq == null) return false;
        if (round == null || round.isEmpty()) return false;
        if (result == null || result.isEmpty()) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "seq=" + seq +
                ", regDate=" + regDate +
                ", leagueSeq=" + leagueSeq +
                ", fighterSeq=" + fighterSeq +
                ", deckSeq=" + deckSeq +
                ", opponentFighterSeq=" + opponentFighterSeq +
                ", opponentDeckSeq=" + opponentDeckSeq +
                ", round='" + round + '\'' +
                ", result='" + result + '\'' +
                ", chkYn='" + chkYn + '\'' +
                '}';
    }
}
