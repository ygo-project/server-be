package com.ygo.server.api.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FighterVO {
    private String userId;
    private String name;
    private String status;

    public boolean isValid() {
        if (userId == null || userId.isEmpty()) return false;
        if (name == null || name.isEmpty()) return false;
        return true;
    }

    @Override
    public String toString() {
        return "FighterVO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
