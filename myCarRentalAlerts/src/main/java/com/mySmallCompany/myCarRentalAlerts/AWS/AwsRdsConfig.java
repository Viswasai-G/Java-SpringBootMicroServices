package com.mySmallCompany.myCarRentalAlerts.AWS;


import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.cloud.aws.jdbc.config.annotation.RdsInstanceConfigurer;
import org.springframework.cloud.aws.jdbc.datasource.TomcatJdbcDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRdsInstance(dbInstanceIdentifier = "${rds.dbname}",
        username = "${rds.username}",
        password = "${rds.password}")
public class AwsRdsConfig {

    @Bean
    public RdsInstanceConfigurer instanceConfigurer(){
        return TomcatJdbcDataSourceFactory::new;
    }
}
