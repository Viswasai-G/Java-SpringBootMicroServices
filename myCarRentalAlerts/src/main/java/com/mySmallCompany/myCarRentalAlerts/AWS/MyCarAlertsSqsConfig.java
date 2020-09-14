package com.mySmallCompany.myCarRentalAlerts.AWS;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class MyCarAlertsSqsConfig {

    private String accessKey;

    private String secretKey;

    private AmazonSQSAsync amazonSQSAsync;

    @Autowired
    public MyCarAlertsSqsConfig(@Value("${amazonsqs.access}") String accessKey,
                          @Value("${amazonsqs.secret}") String secretKey) {
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.amazonSQSAsync = amazonSQSAsyncClient();
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsyncClient(){
        AmazonSQSAsync amazonSQSAsyncClient = AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(this.accessKey, this.secretKey)
                        )
                )
                .withRegion("us-east-2")
                .build();
        return amazonSQSAsyncClient;
    }

}
