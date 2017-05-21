package com.github.aelmod.ssn2.authentication;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginPasswordForm {

    @NotEmpty
    @NotNull
    private String login;

    @NotEmpty
    @NotNull
    private String password;
}
