package com.ygo.server.api.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserVO {
    private String id;
    private String password;
    private String name;

    private LocalDateTime created;

    public boolean isValid() {
        if (id == null || id.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        return true;
    }
}
