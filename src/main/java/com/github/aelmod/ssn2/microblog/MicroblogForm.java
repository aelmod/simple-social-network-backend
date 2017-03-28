package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.user.User;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
public class MicroblogForm {

    @NotNull
    private String text;

    private User user;


    public Microblog toMicroblog() {
        Microblog microblog = new Microblog();
        microblog.setText(text);
        microblog.setCreationTime(new Date());
        microblog.setUser(user);
        return microblog;
    }
}
