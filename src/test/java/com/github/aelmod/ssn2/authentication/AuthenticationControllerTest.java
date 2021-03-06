package com.github.aelmod.ssn2.authentication;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AuthenticationControllerTest {

    private final AuthEndpoint authEndpoint = new AuthEndpoint();

    @Test
    public void loginSuccess() throws Exception {
        authEndpoint.login(getLoginPassword());
    }

    @Test
    public void loginBadPassFailure() throws Exception {
        try {
            LoginPasswordForm loginPasswordForm = getLoginPassword();
            loginPasswordForm.setPassword("badPass");
            authEndpoint.login(loginPasswordForm);
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
        }
    }

    @Test
    public void loginFailure() throws Exception {
        try {
            authEndpoint.login(new LoginPasswordForm());
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

    private LoginPasswordForm getLoginPassword() {
        LoginPasswordForm loginPasswordForm = new LoginPasswordForm();
        loginPasswordForm.setLogin("user1");
        loginPasswordForm.setPassword("pass1");
        return loginPasswordForm;
    }
}