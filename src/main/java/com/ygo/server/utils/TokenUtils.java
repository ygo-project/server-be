package com.ygo.server.utils;

import com.ygo.server.api.service.vo.UserVO;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 관련 토큰 Util
 */
@Log4j2
public class TokenUtils {

//    @Value(value = "${jwt.secret-key}")
    private static final String jwtSecretKey = "exampleSecretKey";
    private static final int ACCESS_TOKEN_VALID_PERIOD = 1; // 1일
    private static final int REFRESH_TOKEN_VALID_PERIOD = 30; // 30일

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     * @param vo UserVO : 사용자 정보
     * @return String : 토큰
     */
    public static String generateAccessToken(UserVO vo) {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(vo))
                .setSubject("accessToken")
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(createExpiredDate(ACCESS_TOKEN_VALID_PERIOD));

        return builder.compact();
    }

    /**
     * 사용자 정보를 기반으로 리프레시 토큰을 생성하여 반환 해주는 메서드
     * @param vo UserVO : 사용자 정보
     * @return String : 토큰
     */
    public static String generateRefreshToken(UserVO vo) {
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(vo))
                .setSubject("refreshToken")
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(createExpiredDate(REFRESH_TOKEN_VALID_PERIOD));

        return builder.compact();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환 해주는 메서드
     * @param token String : 토큰
     * @return String : 사용자 정보
     */
    public static String parseTokenToUserInfo(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 유효한 토큰인지 확인 해주는 메서드
     * @param token String  : 토큰
     * @return boolean      : 유효한지 여부 반환
     */
    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);

//            log.info("expireTime :" + claims.getExpiration());
//            log.info("userId :" + claims.get("userId"));
//            log.info("userNm :" + claims.get("userNm"));

            return true;
        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    /**
     * Header 내에 토큰을 추출합니다.
     * @param header 헤더
     * @return String
     */
    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public static String getUserIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.get("userId").toString();
    }

    /**
     * JWT의 헤더값을 생성
     * @return HashMap<String, Object>
     */
    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    /**
     * 사용자 정보를 기반으로 클래임을 생성해주는 메서드
     * @param vo UserVO : 사용자 정보
     * @return HashMap<String, Object>
     */
    private static Map<String, Object> createClaims(UserVO vo) {
        // 공개 클레임에 사용자의 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

//        log.info("userId : " + vo.getUserId());
//        log.info("name : " + vo.getUserNm());

        claims.put("userId", vo.getUserId());
        claims.put("name", vo.getUserNm());

        return claims;
    }

    /**
     * JWT "서명(Signature)" 발급을 해주는 메서드
     * @return Key
     */
    private static Key createSignature() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 토큰의 만료기간을 지정하는 함수
     * @param day int : 유효 기간(일)
     * @return Date
     */
    private static Date createExpiredDate(int day) {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
     * @param token : 토큰
     * @return Claims : Claims
     */
    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }
}
