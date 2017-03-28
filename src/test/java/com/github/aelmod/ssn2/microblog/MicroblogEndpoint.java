package com.github.aelmod.ssn2.microblog;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class MicroblogEndpoint extends AbstractEndpoint {

    // TODO: 27.03.17 fix this shit
    public ResponseEntity<Microblog> create(MicroblogForm microblogForm) throws Exception {
        HttpHeaders headers = getHttpEntityWithHeaders().getHeaders();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        HttpEntity<MicroblogForm> request = new HttpEntity<>(microblogForm, headers);
        return restTemplate.exchange(baseUrl + "/microblog/create", HttpMethod.POST, request, Microblog.class);
    }

    public ResponseEntity<Microblog> getById(int id) throws Exception {
        return restTemplate.exchange(baseUrl + "/microblog/" + id, HttpMethod.GET, getHttpEntityWithHeaders(), Microblog.class);
    }
}
