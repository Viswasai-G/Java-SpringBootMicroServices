package com.mySmallCompany.myCars.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("User")
                .password("userpass")
                .roles("USER")
                .and()
                .withUser("Admin")
                .password("adminpass")
                .roles("ADMIN");
    }

    /**Spring will encode the password by default.
     *  Override with custom bean to not encode. - For ease of testing*/
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/addCar").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/removeCar/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/**").hasAnyRole("USER", "ADMIN")
                .and()
                /**
                 * To enable authentication for POST and DELETE requests, disable csrf.
                 * But this opens a vulnerability in the application
                 * Alternatively, to secure the application, include a CSRF token in the request.*/
                .csrf().disable();
        /**The below will crete a session token with a GET request.
         * The token is included in the header response from the server
         * Use  this token to authenticate POST and DELETE requests for that user.*/
//            http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);

    }

}
