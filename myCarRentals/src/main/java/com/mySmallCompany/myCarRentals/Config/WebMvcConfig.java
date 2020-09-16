package com.mySmallCompany.myCarRentals.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {

    @Value("${mycars.username}")
    private String myCarsUsername;

    @Value("${mycars.password}")
    private String myCarsPassword;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST");
            }
        };
    }

    @Bean
    @Primary
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Qualifier(value = "myCarsRestTemplate")
    public RestTemplate getMyCarsRestTemplate(){
        return new RestTemplateBuilder().basicAuthentication(this.myCarsUsername, this.myCarsPassword).build();
    }

}
