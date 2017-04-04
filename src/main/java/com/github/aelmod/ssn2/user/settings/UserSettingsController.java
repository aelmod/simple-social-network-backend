package com.github.aelmod.ssn2.user.settings;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @JsonView(UserSettings.MinimalView.class)
    @GetMapping
    public UserSettings getUserSettings(@CurrentUser User user) {
        return userSettingsService.getUserSettings(new UserSettingsSpecification(user.getId()));
    }

    @PostMapping
    public void modifySettings(@CurrentUser User user, @RequestBody UserSettingsForm userSettingsForm) {
        userSettingsForm.setUser(user);
        userSettingsService.updateSettings(userSettingsForm.toUserSettings());
    }
}
