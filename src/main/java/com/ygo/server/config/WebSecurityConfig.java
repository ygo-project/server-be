package com.ygo.server.config;

import com.ygo.server.config.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Spring Security 환경 설정을 구성하기 위한 클래스.
 * 웹 서비스가 로드 될때 Spring Container 의해 관리가 되는 클래스이며 사용자에 대한 ‘인증’과 ‘인가’에 대한 구성을 Bean 메서드로 주입을 한다.
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 1. 정적 자원(Resource)에 대해서 인증된 사용자의 접근에 대해 ‘인가’에 대한 설정을 담당하는 메서드이다.
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //정적 자원에 대해서 security를 사용하지 않음으로 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 2. HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드이다.
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("[+] WebSecurityConfig Start !!! ");
        http
                // [STEP1] 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.
                .csrf().disable()
                // [STEP2] API 서버이기 때문에 cors 정책 off
                .cors().disable()
                // [STEP3] 토큰을 활용하는 경우 모든 요청을 '인가'에 대해서 적용
                .authorizeRequests(authz -> authz.anyRequest().permitAll())
                // [STEP4] Spring Security JWT Filter Load
                .addFilterBefore(new JwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                // [STEP5] Session 기반의 인증기반을 사용하지 않고 JWT를 이용하여서 인증
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // [STEP6] form 기반의 로그인에 대해 비활성화하며 커스텀으로 구성한 필터를 사용한다.
                .formLogin().disable();

        // [STEP7] 최종 구성한 값을 사용함.
        return http.build();
    }
}
