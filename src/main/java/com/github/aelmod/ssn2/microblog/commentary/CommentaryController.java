package com.github.aelmod.ssn2.microblog.commentary;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/microblog/{microblogId}/commentaries")
public class CommentaryController {

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @GetMapping
    @JsonView(Commentary.MinimalView.class)
    public List<Commentary> getAllCommentaries(CommentarySpecification commentarySpecification,
                                               @PathVariable int microblogId) {
        commentarySpecification.setMicroblogId(microblogId);
        return commentaryService.findBy(commentarySpecification);
    }

    @PostMapping("add")
    public void add(@CurrentUser User user, @RequestBody @Valid CommentaryForm commentaryForm,
                    @PathVariable int microblogId) {
        commentaryForm.setUser(user);
        commentaryForm.setMicroblogId(microblogId);
        commentaryService.save(commentaryForm.toCommentary());
    }
}
