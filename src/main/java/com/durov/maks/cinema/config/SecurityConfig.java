package com.durov.maks.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/cinema-halls/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/cinema-halls/**").authenticated()
                .antMatchers(HttpMethod.POST,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movie-sessions/**").authenticated()
                .antMatchers(HttpMethod.POST,"/orders/**").authenticated()
                .antMatchers(HttpMethod.GET,"/orders/**").authenticated()
                .antMatchers("/shopping-carts/**").authenticated()
                .antMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/movies/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movies/**").authenticated()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
