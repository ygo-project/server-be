package com.ygo.server.api.service.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenVO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
