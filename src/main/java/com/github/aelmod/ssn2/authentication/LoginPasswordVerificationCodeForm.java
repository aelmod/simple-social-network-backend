package com.github.aelmod.ssn2.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginPasswordVerificationCodeForm {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String verificationCode;
}
