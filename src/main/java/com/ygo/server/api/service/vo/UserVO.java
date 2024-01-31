package com.ygo.server.api.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserVO {
    private String userId;
    private String userPw;
    private String userNm;

    private LocalDateTime created;

    public boolean isValid() {
        if (userId == null || userId.isEmpty()) return false;
        if (userPw == null || userPw.isEmpty()) return false;
        return true;
    }
}
