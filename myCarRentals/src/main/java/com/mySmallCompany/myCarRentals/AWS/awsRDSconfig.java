package com.mySmallCompany.myCarRentals.AWS;

import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.cloud.aws.jdbc.config.annotation.RdsInstanceConfigurer;
import org.springframework.cloud.aws.jdbc.datasource.TomcatJdbcDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Custom RDS connector configuration.
@Configuration
@EnableRdsInstance(dbInstanceIdentifier = "${rds.dbname}",
        username = "${rds.username}",
        password = "${rds.password}")
public class awsRDSconfig {

    @Bean
    public RdsInstanceConfigurer instanceConfigurer(){
        return () ->{
            TomcatJdbcDataSourceFactory dataSourceFactory =
                    new TomcatJdbcDataSourceFactory();
            dataSourceFactory.setInitialSize(15);
            return dataSourceFactory;
        };
    }

}
