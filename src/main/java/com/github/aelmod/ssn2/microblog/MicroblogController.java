package com.github.aelmod.ssn2.microblog;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("{microblogId}")
    @JsonView(Microblog.FullView.class)
    public Microblog getById(@PathVariable int microblogId) {
        return microblogService.getByPk(microblogId);
    }
}
