package com.springbootweb.config;

import com.springbootweb.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/news").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/news").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/news").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin-user").hasAuthority("ADMIN")
                .antMatchers("/category").hasAuthority("ADMIN")
                .antMatchers("/role").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
