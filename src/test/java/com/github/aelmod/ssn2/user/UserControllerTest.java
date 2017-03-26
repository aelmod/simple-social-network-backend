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
        userRegisterForm.setName("name1337");
        userRegisterForm.setUsername("username1337");
        userRegisterForm.setPassword("13371488");
        userRegisterForm.setConfirmPassword("13371488");
        userRegisterForm.setBirthday(new Date(9999999));
        userRegisterForm.setAddress("address");
        userRegisterForm.setCityId(1);
        userRegisterForm.setCountryId(1);
        userRegisterForm.setEmail("test@mail.ru");
        return userRegisterForm;
    }
}