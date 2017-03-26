package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.user.User;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Setter
public class MicroblogForm {

    @NotNull
    private String text;

    private User user;


    public Microblog toMicroblog() {
        Microblog microblog = new Microblog();
        microblog.setText(text);
        microblog.setCreationTime(getDate());
        microblog.setUser(user);
        return microblog;
    }

    private Date getDate() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
