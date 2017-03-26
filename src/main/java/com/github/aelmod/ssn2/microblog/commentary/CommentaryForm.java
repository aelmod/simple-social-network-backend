package com.github.aelmod.ssn2.microblog.commentary;

import com.github.aelmod.ssn2.microblog.Microblog;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Setter
public class CommentaryForm {

    private String text;

    private Integer microblogId;

    public Commentary toCommentary() {
        Commentary commentary = new Commentary();
        Microblog microblog = new Microblog();
        microblog.setId(microblogId);
        commentary.setCreationTime(getDate());
        commentary.setText(text);
        commentary.setMicroblog(microblog);
        return commentary;
    }

    private Date getDate() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
