package com.struggler.authorization.config;

import com.struggler.authorization.service.impl.StrugglerUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用于保护 oauth 要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
 *
 * @author wanglp
 * @date 2019/5/31
 **/
@Configuration
@EnableWebSecurity
@Slf4j
@Order(2)
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    StrugglerUserDetailService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin()
                .and()
                .requestMatchers().antMatchers("/oauth/**","/auth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .csrf().disable();
        // 禁用缓存
        http.headers().cacheControl();
    }

    /**
     * 注入自定义的userDetailsService实现，获取用户信息，设置密码加密方式
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 将 AuthenticationManager 注册为 bean , 方便配置 oauth server 的时候使用
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
