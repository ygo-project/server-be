package com.ygo.server.api.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeckVO {
    private Long seq;
    private String name;
    private String nickname;
    private String nation;
}
