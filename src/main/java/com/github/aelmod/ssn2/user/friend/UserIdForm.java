package com.github.aelmod.ssn2.user.friend;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserIdForm {

    @NotNull
    private int userId;
}
