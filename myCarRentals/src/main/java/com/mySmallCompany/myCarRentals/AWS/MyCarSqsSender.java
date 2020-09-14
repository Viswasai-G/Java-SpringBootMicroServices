package com.mySmallCompany.myCarRentals.AWS;

import com.amazonaws.auth.*;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.mySmallCompany.myCarRentals.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class MyCarSqsSender {

    private String accessKey;

    private String secretKey;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MyCarSqsSender(@Value("${amazonsqs.access}") String accessKey,
                          @Value("${amazonsqs.secret}") String secretKey) {
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsyncClient());
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

    public void send(Car car){
        this.queueMessagingTemplate
                .convertAndSend("MyCarAlertsQueue", car);
    }
}
