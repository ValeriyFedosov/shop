package edu.karazin.shop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .antMatchers("/cart/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/order/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/user/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/products")
                .failureUrl("/login?Nou").permitAll();

    }
}