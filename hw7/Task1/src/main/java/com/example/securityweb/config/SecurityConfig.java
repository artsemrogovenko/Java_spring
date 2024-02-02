package com.example.securityweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
                .antMatchers("/private/**").hasRole("ADMIN")//страница с правами админа
                .antMatchers("/public/**").authenticated()
                .and()
                .formLogin()
                 .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?error=У%20вас%20нет%20доступа.%20Введите%20данные%20администратора"); // Указываем страницу для случая запрещенного доступа;
    }

    /**
     * Создал 2 пользователя
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
        return manager;
    }
}