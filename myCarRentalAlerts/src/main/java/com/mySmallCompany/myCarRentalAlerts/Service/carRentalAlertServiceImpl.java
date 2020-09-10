package com.mySmallCompany.myCarRentalAlerts.Service;


import com.mySmallCompany.myCarRentalAlerts.Model.Car;
import com.mySmallCompany.myCarRentalAlerts.Model.Issue;
import com.mySmallCompany.myCarRentalAlerts.Model.carRentalAlert;
import com.mySmallCompany.myCarRentalAlerts.Repo.carRentalAlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<carRentalAlert> getAllNotifications() {
        return carRentalAlertRepo.findAll();
    }

    @Override
    public Optional<carRentalAlert> getNotification(String id) {
        return carRentalAlertRepo.findById(id);
    }

    @Override
    public void addNotification(carRentalAlert carNotificationAlert) {
        List<carRentalAlert> alreadyPresent =
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
        List<carRentalAlert> notifications = carRentalAlertRepo.findAll();
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
    public Car getCarDetails(String vin){

        ResponseEntity<Car> responseEntity = restTemplate.getForEntity("http://localhost:8080/getCar/"+vin, Car.class);
        return responseEntity.getBody();
    }

}
