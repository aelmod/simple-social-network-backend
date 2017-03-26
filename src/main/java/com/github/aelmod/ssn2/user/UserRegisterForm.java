package com.github.aelmod.ssn2.user;

import com.github.aelmod.ssn2.city.City;
import com.github.aelmod.ssn2.country.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
public class UserRegisterForm {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 4, max = 12)
    private String username;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    private transient String confirmPassword;
    @NotNull
    private Date birthday;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private int countryId;
    @NotNull
    private int cityId;
    @NotNull
    private String address;

    @AssertTrue(message = "The passwords you provided do not match. Please correct and resubmit.")
    private boolean isValidConfirmedPassword() {
        return Objects.equals(password, confirmPassword);
    }

    /**
     * In frontend helper - Available phone number formats:
     * +380630000000, 0630000000
     * 063-000-0000 x1234, 063-000-0000 ext1234,
     * 063-000-0000, (063)-000-0000,
     * 063.000.0000, 063 000 0000
     */
    @AssertTrue(message = "Invalid phone number format")
    private boolean isValidPhoneNumber() {
        if (phone.matches("[0-9*#+() -]{13}")) return true;
        if (phone.matches("\\d{10}")) return true;
        if (phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        if (phone.matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}")) return true;
        if (phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        return false;
    }

    public User toUser() {
        Country country = new Country();
        country.setId(countryId);
        City city = new City();
        city.setId(cityId);
        return new User(name, username, password, birthday, email, phone, country, city, address);
    }
}
