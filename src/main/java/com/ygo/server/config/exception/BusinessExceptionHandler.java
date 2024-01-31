package com.ygo.server.config.exception;

import com.ygo.server.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;

/**
 * 에러를 사용하기 위한 구현체
 */
@Getter
public class BusinessExceptionHandler extends RuntimeException{

    private final ErrorCode errorCode;

    @Builder
    public BusinessExceptionHandler(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public BusinessExceptionHandler(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
