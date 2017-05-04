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
            LoginPasswordVerificationCodeForm loginPasswordVerificationCodeForm = getLoginPassword();
            loginPasswordVerificationCodeForm.setPassword("badPass");
            authEndpoint.login(loginPasswordVerificationCodeForm);
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
        }
    }

    @Test
    public void loginFailure() throws Exception {
        try {
            authEndpoint.login(new LoginPasswordVerificationCodeForm());
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

    private LoginPasswordVerificationCodeForm getLoginPassword() {
        LoginPasswordVerificationCodeForm loginPasswordVerificationCodeForm = new LoginPasswordVerificationCodeForm();
        loginPasswordVerificationCodeForm.setLogin("user1");
        loginPasswordVerificationCodeForm.setPassword("pass1");
        return loginPasswordVerificationCodeForm;
    }
}