package com.challenge.picpay.core.usecases.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.domain.dtos.NotificationDTO;

@Service
public class SendNotificationUseCase {
  @Autowired
  private RestTemplate restTemplate;

  public void perform(User user, String message) throws Exception {
    String email = user.getEmail();
    NotificationDTO notification = new NotificationDTO(email, message);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<NotificationDTO> notificationRequest = new HttpEntity<>(notification, headers);

    ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:8080/picpay",
        notificationRequest,
        String.class);

    if (!(response.getStatusCode() == HttpStatus.OK)) {
      System.out.println(response.getBody());
      throw new Exception("Notification failed!");
    }
    System.out.println(response.getBody());

  }
}
