package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class MicroblogEndpoint extends AbstractEndpoint {

    public ResponseEntity<Microblog> create(MicroblogForm microblogForm) throws Exception {
        return restTemplate.exchange(baseUrl + "/microblog/create", HttpMethod.POST, getHeaders(), Microblog.class);
    }

    public ResponseEntity<Microblog> getById(int id) throws Exception {
        return restTemplate.exchange(baseUrl + "/microblog/" + id, HttpMethod.GET, getHeaders(), Microblog.class);
    }
}
