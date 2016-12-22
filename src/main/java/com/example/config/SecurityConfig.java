package com.example.config;

import com.example.handler.AdminFailureHandler;
import com.example.handler.AdminSuccessHandler;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy(proxyTargetClass = true) // 없으면 aop를 걸면 페이지가 넘어가지 않음.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/static/**","/bower_components/**","/css/**","/js/**","/fonts/**","/images/**","/join", "/console/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin() // 로그인
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/main")
                .successHandler(new AdminSuccessHandler()) // 성공하면 수행하고 넘어가야 하는 클래스 모음
                .failureHandler(new AdminFailureHandler()) // 실패하면 수행하고 넘어가야 하는 클래스 모음
                .permitAll() // 커스텀 로그인 및 모든 권한
                .and()
                .logout() // 로그아웃
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .sessionManagement() // 세션
                .maximumSessions(1) // 최대 1명만
                .maxSessionsPreventsLogin(true) // 중복 로그인 관련
                .expiredUrl("/login?expired") // 중복 로그인 관련
                .sessionRegistry(sessionRegistry()); // 중복 로그인 관련

        http.csrf().disable();
        http.headers().frameOptions().disable(); // h2 console 추가
    }

//    @Bean
//    org.h2.tools.Server h2Server() { // 화면 구동시 바로 h2console를 띄운다.
//        Server server = new Server();
//        try {
//            server.runTool("-tcp");
//            server.runTool("-tcpAllowOthers");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return server;
//
//    }

    @Bean
    SessionRegistry sessionRegistry() { // 중복 로그인 관련
        return new SessionRegistryImpl();
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() { // 중복 로그인 관련
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }
}
