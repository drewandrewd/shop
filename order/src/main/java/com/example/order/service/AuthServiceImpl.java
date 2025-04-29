package com.example.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;

    @Override
    public boolean userExist(Long id) {
        try {
            restTemplate.getForEntity("http://auth-service/users/" + id, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
