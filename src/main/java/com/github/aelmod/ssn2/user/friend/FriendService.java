package com.github.aelmod.ssn2.user.friend;

import com.github.aelmod.ssn2.user.BadUserBehaviorException;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserRepository;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class FriendService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(UserRepository userRepository, UserService userService, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.friendRepository = friendRepository;
    }

    @Transactional
    public void makeFriends(User user1, User user2) {
        if (Objects.equals(user1.getId(), user2.getId()))
            throw new BadUserBehaviorException("You can not add yourself to friends");
        user1 = userService.getByPk(user1.getId());
        user2 = userService.getByPk(user2.getId());
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        userRepository.persist(user1);
        userRepository.persist(user2);
    }

    @Transactional
    public void requestFriendship(User user, Integer requestedFriendshipUserId) {
        User theoreticalFriend = userService.getByPk(requestedFriendshipUserId);
        user = userService.getByPk(user.getId());
        user.getFriendRequestsBucket().forEach(userConsumer -> {
            if (userConsumer.getId().equals(requestedFriendshipUserId))
                throw new BadUserBehaviorException("You have already invited this user");
        });
        user.getFriendRequestsBucket().add(theoreticalFriend);
        userRepository.persist(user);
    }

    @Transactional
    public void acceptFriendshipRequest(User user, Integer theoreticalFriendId) {
        User theoreticalFriend = userService.getByPk(theoreticalFriendId);
        theoreticalFriend.getFriendRequestsBucket().remove(user);
        makeFriends(user, theoreticalFriend);
        userRepository.persist(user);
        userRepository.persist(theoreticalFriend);
    }

    @Transactional
    public void rejectFriendshipRequest(User user, Integer theoreticalFriendId) {
        User theoreticalFriend = userService.getByPk(theoreticalFriendId);
        theoreticalFriend.getFriendRequestsBucket().remove(user);
        userRepository.persist(theoreticalFriend);
    }

    @Transactional(readOnly = true)
    public List<User> getIncomingFriendshipRequests(User user) {
        return friendRepository.getIncomingFriendshipRequests(user.getId());
    }
}
