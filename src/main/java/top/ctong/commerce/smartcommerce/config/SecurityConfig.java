package top.ctong.commerce.smartcommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.ctong.commerce.smartcommerce.filters.JwtAuthorizationFilter;

import java.util.Collections;
import java.util.List;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 * security 配置类
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-01-03 15:03
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${spring.security.permit-paths}")
    private String[] permitPaths;

    /**
     * 设置全局加密工具
     * @return BCryptPasswordEncoder
     * @author Clover You
     * @date 2023/1/3 15:04
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置 spring security 忽略路径
     * @return WebSecurityCustomizer
     * @author Clover You
     * @date 2023/1/3 15:42
     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> {
//            web.ignoring().antMatchers("/static/**");
//        };
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 使用 jwt 所以不需要跨域
        return http.csrf().disable()
            // 使用 jwt 来鉴权所以不需要 session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 设置权限
            .authorizeRequests()
            .antMatchers(permitPaths)
            .permitAll()
            .anyRequest().authenticated().and()
            .headers().cacheControl().disable().and()
            .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    public UserDetailsService userDetailsService() {
        return username -> null;
    }

    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

}
