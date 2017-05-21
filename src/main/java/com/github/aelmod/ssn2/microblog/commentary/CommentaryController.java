package com.github.aelmod.ssn2.microblog.commentary;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.microblog.MicroblogService;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.settings.NotRelevantUserPrivacySettingsException;
import com.github.aelmod.ssn2.user.settings.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/microblog/{microblogId}/commentaries")
public class CommentaryController {

    private final MicroblogService microblogService;

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(MicroblogService microblogService, CommentaryService commentaryService) {
        this.microblogService = microblogService;
        this.commentaryService = commentaryService;
    }

    @GetMapping
    @JsonView(Commentary.WithUser.class)
    public List<Commentary> getAllCommentaries(CommentarySpecification commentarySpecification,
                                               @PathVariable int microblogId) {
        commentarySpecification.setMicroblogId(microblogId);
        return commentaryService.findBy(commentarySpecification);
    }

    @PostMapping
    @JsonView(Commentary.WithUser.class)
    public Commentary add(@CurrentUser User user, @RequestBody @Valid CommentaryForm commentaryForm,
                    @PathVariable int microblogId) {
        checkCommentaryPrivacySettings(user, microblogId);
        commentaryForm.setUser(user);
        commentaryForm.setMicroblogId(microblogId);
        Commentary commentary = commentaryForm.toCommentary();
        commentaryService.save(commentary);
        return commentaryService.findById(commentary.getId());
    }

    private void checkCommentaryPrivacySettings(@CurrentUser User user, @PathVariable int microblogId) {
        Microblog rootMicroblog = microblogService.getByPk(microblogId);
        User microblogAuthor = rootMicroblog.getUser();
        if (Objects.isNull(microblogAuthor.getUserSettings())) return;
        if (microblogAuthor.getUserSettings().getCommentaryPrivacy() == UserSettings.CommentaryPrivacy.ONLY_FRIENDS) {
            List<User> friends = microblogAuthor.getFriends();
            friends.forEach(friend -> {
                if (!Objects.equals(friend.getId(), user.getId()))
                    throw new NotRelevantUserPrivacySettingsException("User allowed to comment on his microblog only to friends");
            });
        }
    }
}
