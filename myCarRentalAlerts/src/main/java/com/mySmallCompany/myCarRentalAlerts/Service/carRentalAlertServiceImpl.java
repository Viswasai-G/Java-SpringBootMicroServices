package com.mySmallCompany.myCarRentalAlerts.Service;


import com.mySmallCompany.myCarRentalAlerts.Model.CarReading;
import com.mySmallCompany.myCarRentalAlerts.Model.Issue;
import com.mySmallCompany.myCarRentalAlerts.Model.CarRentalAlert;
import com.mySmallCompany.myCarRentalAlerts.Repo.carRentalAlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

@Service
public class carRentalAlertServiceImpl implements carRentalAlertService {


    @Autowired
    carRentalAlertRepo carRentalAlertRepo;

    @Autowired
    ExecutorService executorService;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<CarRentalAlert> getAllNotifications() {
        return carRentalAlertRepo.findAll();
    }

    @Override
    public Optional<CarRentalAlert> getNotification(String id) {
        return carRentalAlertRepo.findById(id);
    }

    /**Default implementation. Will add a notification iff it has not been reported yet.
     * Will trigger the below function through the controller.
     * */
    @Override
    public void addNotification(CarRentalAlert carNotificationAlert) {
        List<CarRentalAlert> alreadyPresent =
                carRentalAlertRepo.findAll()
                        .stream()
                        .filter(notif -> notif.getIssue()==carNotificationAlert.getIssue())
                        .filter(notif -> notif.getVin().equals(carNotificationAlert.getVin()))
                        .collect(Collectors.toList());

        if(alreadyPresent.isEmpty()){
            carRentalAlertRepo.save(carNotificationAlert);
        }
    }

    /**SQS implementation.
     * Will trigger the below function whenever there is an object present in SQS.
     * */
    @SqsListener(value = "MyCarAlertsQueue")
    public void addNotificationFromSQS(CarReading carReading){
        //Adding issue as low fuel to differentiate messages coming from SQS.
        CarRentalAlert carNotificationAlert = new CarRentalAlert(carReading.getVin(), Issue.LOW_FUEL);
        List<CarRentalAlert> alreadyPresent =
                carRentalAlertRepo.findAll()
                        .stream()
                        .filter(notif -> notif.getIssue()==carNotificationAlert.getIssue())
                        .filter(notif -> notif.getVin().equals(carNotificationAlert.getVin()))
                        .collect(Collectors.toList());

        if(alreadyPresent.isEmpty()){
            carRentalAlertRepo.save(carNotificationAlert);
        }
    }

    @Override
    public List<String> getIssueRelated(String issue) {
        executorService.submit(() -> {
            try {
                sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        List<CarRentalAlert> notifications = carRentalAlertRepo.findAll();
        return notifications.stream()
                .filter(notif -> notif.getIssue()== Issue.valueOf(issue))
                .map(notif -> notif.getVin())
                .collect(Collectors.toList());
    }

    @Override
    public void issueResolved(String id) {
        //Assume Db is slow in deleting. Handling the process async. Returns 200 OK immediately
        //i.e. handing blocking operations to separate thread.
        executorService.submit(() ->{
            try {
                sleep(7000);
                carRentalAlertRepo.deleteById(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CarReading getCarDetails(String vin){

        ResponseEntity<CarReading> responseEntity = restTemplate.getForEntity("http://localhost:8080/getCar/"+vin, CarReading.class);
        return responseEntity.getBody();
    }

}
