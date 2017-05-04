package com.github.aelmod.ssn2.authentication;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginPasswordVerificationCodeForm {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String verificationCode;
}
