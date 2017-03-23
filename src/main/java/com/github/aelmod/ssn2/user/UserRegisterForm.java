package com.github.aelmod.ssn2.user;


import com.github.aelmod.ssn2.country.Country;
import com.github.aelmod.ssn2.country.CountryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;

@Getter
@Setter
public class UserRegisterForm {
    private String name;
    private String username;
    private String password;
    private transient String repeatedPassword;
    private Date birthday;
    private String email;
    private int phone;
    private int countryId;
    private int cityId;
    private String address;

    public User toUser() {
        Country country = new Country();
        country.setId(countryId);
        return new User(null, name, username, password, birthday, email, phone, country, cityId, address, new HashSet<>());
    }
}
