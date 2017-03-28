package com.github.aelmod.ssn2.microblog;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MicroblogControllerTest {

    private final MicroblogEndpoint microblogEndpoint = new MicroblogEndpoint();

    private final int MICROBLOG_ID = 1;

    @Test
    public void createSuccess() throws Exception {
        microblogEndpoint.create(getMicroblogForm());
    }

    @Test
    public void createFailure() throws Exception {
        try {
            microblogEndpoint.create(new MicroblogForm());
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

    @Test
    public void getById() throws Exception {
        microblogEndpoint.getById(MICROBLOG_ID);
    }

    private MicroblogForm getMicroblogForm() {
        MicroblogForm microblogForm = new MicroblogForm();
        microblogForm.setText("easy peasy lemon squeezy");
        return microblogForm;
    }
}