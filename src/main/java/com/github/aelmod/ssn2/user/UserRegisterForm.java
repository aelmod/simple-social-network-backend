package com.github.aelmod.ssn2.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
public class UserRegisterForm {

    @NotNull
    private String fullName;

    @NotNull
    @Size(min = 4, max = 12)
    private String username;

    @NotNull
    @Size(min = 8, max = 32)
    private String password;

    private transient String confirmPassword;

    @NotNull
    @Email
    private String email;

    @AssertTrue(message = "The passwords you provided do not match. Please correct and resubmit.")
    private boolean isValidConfirmedPassword() {
        return Objects.equals(password, confirmPassword);
    }

    public User toUser() {
        return new User(fullName, email, username, password);
    }
}
