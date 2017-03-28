package com.github.aelmod.ssn2.user;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserControllerTest {

    private final UserEndpoint userEndpoint = new UserEndpoint();

    private final int TEST_USER_ID = 1;

    @Test
    public void getAll() throws Exception {
        userEndpoint.getAll();
    }

    @Test
    public void getById() throws Exception {
        userEndpoint.getById(TEST_USER_ID);
    }

    @Test
    public void getFriends() throws Exception {
        userEndpoint.getFriends(TEST_USER_ID);
    }

    @Test
    public void registerUserFailure() {
        try {
            UserEndpoint userEndpoint = new UserEndpoint();
            userEndpoint.register(new UserRegisterForm());
            fail();
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

    @Test
    public void registerUserSuccess() {
        userEndpoint.register(getUserRegisterForm());
    }

    private UserRegisterForm getUserRegisterForm() {
        UserRegisterForm userRegisterForm = new UserRegisterForm();
        userRegisterForm.setName(getRandomString());
        userRegisterForm.setUsername(getRandomString());
        userRegisterForm.setPassword("1337_Pass");
        userRegisterForm.setConfirmPassword("1337_Pass");
        userRegisterForm.setBirthday(new Date());
        userRegisterForm.setAddress("address");
        userRegisterForm.setCityId(1);
        userRegisterForm.setCountryId(1);
        userRegisterForm.setEmail("test@mail.ru");
        return userRegisterForm;
    }

    private String getRandomString() {
        return Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 12);
    }
}