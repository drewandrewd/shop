package com.example.order.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthServiceClient {

    private final RestTemplate restTemplate;

    @Value("${auth.client.username}")
    private String username;

    @Value("${auth.client.password}")
    private String password;

        public void userExist(Long id) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(username, password);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    "http://auth-service/auth/" + id,
                    HttpMethod.GET,
                    entity,
                    Void.class
            );
    }
}
