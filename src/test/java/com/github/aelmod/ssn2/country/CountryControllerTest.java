package com.github.aelmod.ssn2.country;

import org.junit.Test;

public class CountryControllerTest {

    private final CountryEndpoint countryEndpoint = new CountryEndpoint();

    private final int TEST_COUNTRY_ID = 1;

    @Test
    public void getAll() throws Exception {
        countryEndpoint.getAll();
    }

    @Test
    public void getById() throws Exception {
        countryEndpoint.getById(TEST_COUNTRY_ID);
    }

}