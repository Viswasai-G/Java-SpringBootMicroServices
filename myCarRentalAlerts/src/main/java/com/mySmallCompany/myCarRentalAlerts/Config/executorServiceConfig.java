package com.mySmallCompany.myCarRentalAlerts.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class executorServiceConfig {

    @Bean
    public ExecutorService createAsyncProc(){
        return Executors.newFixedThreadPool(4);
    }

}
