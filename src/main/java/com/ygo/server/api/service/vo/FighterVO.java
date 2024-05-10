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
    private Long seq;
    private String userId;
    private String name;
    private String status;

    public boolean isValid() {
        if (seq == null || seq.equals(0L)) return false;
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
