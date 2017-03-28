package com.github.aelmod.ssn2.country;

import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class CountryEndpoint extends AbstractEndpoint {

    public ResponseEntity<Country[]> getAll() {
        return restTemplate.exchange(baseUrl + "/countries", HttpMethod.GET, getHttpEntityWithHeaders(), Country[].class);
    }

    public ResponseEntity<Country> getById(int id) {
        return restTemplate.exchange(baseUrl + "/countries/" + id, HttpMethod.GET, getHttpEntityWithHeaders(), Country.class);
    }
}
