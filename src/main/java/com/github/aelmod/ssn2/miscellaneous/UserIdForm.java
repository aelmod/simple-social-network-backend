package com.github.aelmod.ssn2.miscellaneous;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserIdForm {

    @NotNull
    private Integer userId;
}
