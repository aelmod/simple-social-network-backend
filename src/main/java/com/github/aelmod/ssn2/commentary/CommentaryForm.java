package com.github.aelmod.ssn2.commentary;

import com.github.aelmod.ssn2.user.User;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Setter
public class CommentaryForm {
    private String text;
    private User repliedToUser;

    public Commentary toCommentary() {
        Commentary commentary = new Commentary();
        commentary.setCreationTime(getDate());
        return commentary;
    }

    private Date getDate() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
