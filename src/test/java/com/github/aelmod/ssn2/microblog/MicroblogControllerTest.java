package com.github.aelmod.ssn2.microblog;

import org.junit.Test;

public class MicroblogControllerTest {

    private final MicroblogEndpoint microblogEndpoint = new MicroblogEndpoint();

    private final int MICROBLOG_ID = 1;

    @Test
    public void create() throws Exception {
        microblogEndpoint.create(getMicroblogForm());
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