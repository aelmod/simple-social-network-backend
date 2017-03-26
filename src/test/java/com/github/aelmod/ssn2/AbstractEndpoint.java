package com.github.aelmod.ssn2;

import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Getter
public abstract class AbstractEndpoint {

    public final RestTemplate restTemplate = new RestTemplate();

    public final String baseUrl = "http://127.0.0.1:8080/api";

    public HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Token", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTaW1wbGVTb2NpYWxOZXR3b3JrMiIsInVzZXJJZCI6Mn0.LXjsEIVtA8ClS2OmMN8emaHQHUwwRTdtC84NoHRllIA5yci4w5m1Wo2rqEKHSYhbhZfgpurzzIZziMrkVfOjgg");
        return new HttpEntity<>(headers);
    }
}
