package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MicroblogForm {
    private String text;
    private LocalDateTime localDate = LocalDateTime.now();
    private User user;

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(@CurrentUser User user) {
        this.user = user;
    }

    public Microblog toMicroblog() {
        Microblog microblog = new Microblog();
        microblog.setText(text);
        microblog.setCreationDate(getDate());
        microblog.setUser(user);
        return microblog;
    }

    private Date getDate() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
