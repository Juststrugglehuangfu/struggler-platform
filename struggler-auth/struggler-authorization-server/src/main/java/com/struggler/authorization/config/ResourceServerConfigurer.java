package com.struggler.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源认证服务器
 * @author wanglp
 * @date 2019/6/2
 **/
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests().antMatchers("/druid/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }
}
