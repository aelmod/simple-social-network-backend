package com.github.aelmod.ssn2.microblog;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/microblog")
public class MicroblogController {

    private final MicroblogService microblogService;

    @Autowired
    public MicroblogController(MicroblogService microblogService) {
        this.microblogService = microblogService;
    }

    @PostMapping("create")
    @JsonView(Microblog.FullView.class)
    public Microblog create(@CurrentUser User user, @RequestBody @Valid MicroblogForm microblogForm) {
        microblogForm.setUser(user);
        Microblog microblog = microblogForm.toMicroblog();
        microblogService.create(microblog);
        return microblogService.getByPk(microblog.getId());
    }

    @GetMapping("{microblogId}")
    @JsonView(Microblog.FullView.class)
    public Microblog getById(@PathVariable int microblogId) {
        return microblogService.getByPk(microblogId);
    }
}
