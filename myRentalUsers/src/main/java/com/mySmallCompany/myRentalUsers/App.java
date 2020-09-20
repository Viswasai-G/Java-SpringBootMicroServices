package com.mySmallCompany.myRentalUsers;


import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan
@EnableWebMvc
@Configuration
/**
 * Not working - Need to research further into enabling service discovery with Eureka for native Spring apps
 *
 * Does not throw exception/server init. Server starts up normally and api works fine. The service is not
 * registering itself to Eureka though.*/
@EnableEurekaClient
public class App {

}
