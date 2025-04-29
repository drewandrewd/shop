package com.example.order.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class AuthServiceClient {

    private final RestTemplate restTemplate;
    
    public boolean userExist(Long id) {
        try {
            restTemplate.getForEntity("http://auth-service/users/" + id, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
