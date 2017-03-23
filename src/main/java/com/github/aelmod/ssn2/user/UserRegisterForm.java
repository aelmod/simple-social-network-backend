package com.github.aelmod.ssn2.user;


import com.github.aelmod.ssn2.country.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
public class UserRegisterForm {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 6, max = 10)
    private String username;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    private transient String confirmPassword;
    @NotNull
    private Date birthday;
    @NotNull
    private String email;
    private int phone;
    @NotNull
    private int countryId;
    @NotNull
    private int cityId;
    private String address;

    @AssertTrue(message = "The passwords you provided do not match. Please correct and resubmit.")
    private boolean isValidConfirmedPassword() {
        return Objects.equals(password, confirmPassword);
    }

    public User toUser() {
        Country country = new Country();
        country.setId(countryId);
        return new User(null, name, username, password, birthday, email, phone, country, cityId, address, new HashSet<>());
    }
}
