package com.ygo.server.config.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygo.server.config.exception.BusinessExceptionHandler;
import com.ygo.server.config.exception.TokenNotValidateException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotValidateException | BusinessExceptionHandler e) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, e);
            return;
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable e) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(new ErrorResponse(status, e.getMessage()).convertToJson());
    }

    @Getter
    public static class ErrorResponse {

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private final String timestamp;
        private final int status;
        private final String error;
        private final String message;

        public ErrorResponse(HttpStatus status, String message) {
            this.timestamp = new Date().toString();
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
        }

        public String convertToJson() throws JsonProcessingException {
            return objectMapper.writeValueAsString(this);
        }

    }
}
