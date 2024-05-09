package com.ygo.server.api.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueVO {
    Long seq;
    String userId;
    String name;

    public boolean isValid() {
        if (userId == null || userId.isEmpty()) return false;
        if (name == null || name.isEmpty()) return false;
        return true;
    }
}
