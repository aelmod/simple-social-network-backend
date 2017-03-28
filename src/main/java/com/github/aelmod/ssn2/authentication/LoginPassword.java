package com.github.aelmod.ssn2.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginPassword {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
