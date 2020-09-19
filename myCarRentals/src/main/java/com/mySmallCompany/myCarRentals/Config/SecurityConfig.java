package com.mySmallCompany.myCarRentals.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** Configured all endpoints except localhost:8084/login, localhost:8084/ , and localhost:8084/error.
     * These endpoints have not been configured yet. All endpoints defined in the controller require authentication
     * and authorization from Okta auth server.
     *
     * TO-DO: Automate auth requests to okta server since call is not to an external resource. */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login**","/error**").permitAll()
                .anyRequest().authenticated()
                .and().oauth2Login()
        .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
