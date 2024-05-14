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
public class CustomLeagueVO {
    private Date regDate;
    private String fighter;
    private String deck;
    private String league;
}
