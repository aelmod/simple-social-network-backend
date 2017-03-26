package com.github.aelmod.ssn2.microblog.commentary;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Commentary> getAllCommentaries(CommentarySpecification commentarySpecification, @PathVariable int microblogId) {
        commentarySpecification.setMicroblogId(microblogId);
        return commentaryService.findBy(commentarySpecification);
    }

    @PostMapping("add")
    public void add(@RequestBody CommentaryForm commentaryForm, @PathVariable int microblogId) {
        commentaryForm.setMicroblogId(microblogId);
        commentaryService.save(commentaryForm.toCommentary());
    }
}
