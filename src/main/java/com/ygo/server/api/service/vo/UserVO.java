package com.ygo.server.api.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String userId;
    private String userPw;
    private String userNm;

    private String userAuth;

    private LocalDateTime created;

    public boolean isValid() {
        if (userId == null || userId.isEmpty()) return false;
        if (userPw == null || userPw.isEmpty()) return false;
        return true;
    }
}
