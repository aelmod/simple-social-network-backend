package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/microblog")
public class MicroblogController {
    private final MicroblogService microblogService;

    @Autowired
    public MicroblogController(MicroblogService microblogService) {
        this.microblogService = microblogService;
    }

    @PostMapping("create")
    public void create(@CurrentUser User user, @RequestBody MicroblogForm microblogForm) {
        microblogForm.setUser(user);
        microblogService.create(microblogForm.toMicroblog());
    }
}
