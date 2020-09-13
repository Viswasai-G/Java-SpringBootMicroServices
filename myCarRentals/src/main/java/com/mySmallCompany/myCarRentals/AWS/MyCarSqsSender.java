package com.mySmallCompany.myCarRentals.AWS;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.mySmallCompany.myCarRentals.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyCarSqsSender {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MyCarSqsSender(AmazonSQSAsync amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
    }

    public void send(Car car){
        this.queueMessagingTemplate
                .convertAndSend("MyCarAlertsQueue", car);
    }
}
