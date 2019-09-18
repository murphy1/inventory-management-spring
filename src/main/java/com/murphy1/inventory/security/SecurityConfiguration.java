package com.murphy1.inventory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users").hasAuthority("ADMIN")
                .antMatchers("/update/wallet/*").hasAnyAuthority("ADMIN", "FINANCE")
                .antMatchers("/groceries").hasAnyAuthority("ADMIN", "FINANCE", "USER")
                .antMatchers("/games").hasAnyAuthority("ADMIN", "FINANCE", "USER")
                .antMatchers("/electronics").hasAnyAuthority("ADMIN", "FINANCE", "USER")
                .antMatchers("/furniture").hasAnyAuthority("ADMIN", "FINANCE", "USER")
                .antMatchers("/search").hasAnyAuthority("ADMIN", "FINANCE", "USER")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
