package com.ygo.server.config.filter;

import com.ygo.server.api.service.interfaces.UserService;
import com.ygo.server.config.exception.BusinessExceptionHandler;
import com.ygo.server.config.exception.TokenNotValidateException;
import com.ygo.server.constants.AuthConstants;
import com.ygo.server.constants.ErrorCode;
import com.ygo.server.utils.TokenUtils;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 1. 토큰이 필요하지 않은 API URL에 대해서 배열로 구성합니다.
        List<String> urls = Arrays.asList(
                "/user/find/",
                "/user/login",
                "/user/refresh",
                "/user/signup"
        );

        // 2. 토큰이 필요하지 않은 API URL의 경우 => 로직 처리 없이 다음 필터로 이동
        for (String url : urls) {
            if (request.getRequestURI().startsWith(url)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // 3. OPTIONS 요청일 경우 => 로직 처리 없이 다음 필터로 이동
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        // [STEP1] Client에서 API를 요청할때 Header를 확인합니다.
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        logger.debug("[+] header Check: " + header);

        try {
            // [STEP2-1] Header 내에 토큰이 존재하는 경우
            if (header != null && !header.equalsIgnoreCase("")) {

                // [STEP2] Header 내에 토큰을 추출합니다.
                String token = TokenUtils.getTokenFromHeader(header);

                // [STEP3] 추출한 토큰이 유효한지 여부를 체크합니다.
                if (TokenUtils.isValidToken(token)) {

                    // [STEP4] 토큰을 기반으로 사용자 아이디를 반환 받는 메서드
                    String userId = TokenUtils.getUserIdFromToken(token);
                    logger.debug("[+] userId Check: " + userId);

                    // [STEP5] 사용자 아이디가 존재하는지 여부 체크
                    if (userId != null && !userId.equalsIgnoreCase("")) {
                        // [STEP6] 실제 DB로 조회를 하여 유효한 사용자 인지 확인(인증)
                        if (userService.isExistID(userId)) chain.doFilter(request, response);
                    } else {
                        throw new BusinessExceptionHandler("TOKEN isn't userId", ErrorCode.BUSINESS_EXCEPTION_ERROR);
                    }
                    // 토큰이 유효하지 않은 경우
                } else {
                    throw new BusinessExceptionHandler("TOKEN is invalid", ErrorCode.BUSINESS_EXCEPTION_ERROR);
                }
            }
            // [STEP2-1] 토큰이 존재하지 않는 경우
            else {
                throw new BusinessExceptionHandler("Token is null", ErrorCode.BUSINESS_EXCEPTION_ERROR);
            }
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT Token", e);
            throw new TokenNotValidateException("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token", e);
            throw new TokenNotValidateException("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
            throw new TokenNotValidateException("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
            throw new TokenNotValidateException("JWT claims string is empty", e);
        } catch (SignatureException e) {
            log.error("JWT Token Not Allowed", e);
            throw new TokenNotValidateException("JWT Token Not Allowed", e);
        } catch (JwtException e) {
            log.error("TOKEN Parsing JwtException", e);
            throw new TokenNotValidateException("TOKEN Parsing JwtException", e);
        }
    }
}
