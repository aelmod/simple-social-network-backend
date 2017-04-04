package com.github.aelmod.ssn2.user.settings;

import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
public class UserSettingsForm {

    private User user;

    @NotNull
    private UserSettings.CommentaryPrivacy commentaryPrivacy;

    public UserSettings toUserSettings() {
        UserSettings userSettings = user.getUserSettings();

        if (Objects.isNull(userSettings)) {
            UserSettings newUserSettings = new UserSettings();
            newUserSettings.setCommentaryPrivacy(commentaryPrivacy);
            newUserSettings.setUser(user);
            return newUserSettings;
        }

        userSettings.setCommentaryPrivacy(commentaryPrivacy);
        userSettings.setUser(user);
        return userSettings;
    }
}
