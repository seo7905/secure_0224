package com.sist.secure_0224.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions( // -> 람다형식: 이쪽에 들어오는 객체를 받아서 인자를 준다. -> 함수이름(부른다)
                HeadersConfigurer.FrameOptionsConfig::sameOrigin
            )).authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers("/**","/reg","/login","/regLogin","/logout")
                    .permitAll().anyRequest().authenticated()
            );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
