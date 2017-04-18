package com.github.aelmod.ssn2.user.friend;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class FriendController {

    private final FriendService friendService;

    private final UserService userService;

    @Autowired
    public FriendController(FriendService friendService, UserService userService) {
        this.friendService = friendService;
        this.userService = userService;
    }

    @GetMapping("{userId}/friends")
    @JsonView(User.AllPrimitivesView.class)
    public List<User> getFriends(@PathVariable Integer userId) {
        return userService.getByPk(userId).getFriends();
    }

    @GetMapping("friends/outgoingRequests")
    @JsonView(User.MinimalView.class)
    public List<User> getOutgoingFriendshipRequests(@CurrentUser User currentUser) {
        return currentUser.getFriendRequestsBucket();
    }

    @GetMapping("friends/incomingRequests")
    @JsonView(User.MinimalView.class)
    public List<User> getIncomingFriendshipRequests(@CurrentUser User currentUser) {
        return friendService.getIncomingFriendshipRequests(currentUser);
    }

    @PostMapping("friends")
    public void requestFriendship(@CurrentUser User currentUser, @RequestBody @Valid UserIdForm requestedFriendshipUserId) {
        friendService.requestFriendship(currentUser, requestedFriendshipUserId.getUserId());
    }

    @PutMapping("friends")
    public void acceptFriendshipRequest(@CurrentUser User user, @RequestBody @Valid UserIdForm userId) {
        friendService.acceptFriendshipRequest(user, userId.getUserId());
    }

    @DeleteMapping("friends")
    public void rejectFriendshipRequest(@CurrentUser User user, @RequestBody @Valid UserIdForm userId) {
        friendService.rejectFriendshipRequest(user, userId.getUserId());
    }
}
