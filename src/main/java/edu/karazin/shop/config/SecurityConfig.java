package edu.karazin.shop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static edu.karazin.shop.model.Role.ROLE_ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    @Autowired
    public SecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/product/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/cart/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ANONYMOUS')")
                .antMatchers("/order/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_ANONYMOUS')")
                .antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/products")
                .failureUrl("/login?Nou").permitAll();

    }
}