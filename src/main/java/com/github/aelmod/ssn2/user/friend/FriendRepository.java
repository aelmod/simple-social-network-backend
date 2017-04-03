package com.github.aelmod.ssn2.user.friend;

import com.github.aelmod.ssn2.BaseRepository;
import com.github.aelmod.ssn2.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendRepository extends BaseRepository<User, Integer> {

    public FriendRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<User> getIncomingFriendshipRequests(Integer userId) {
        String jpql = "select user from User as user join user.friendRequestsBucket friendRequestsBucket where friendRequestsBucket.id=:userId";
        List<User> requestedFriendshipUsers = entityManager.createQuery(jpql, User.class)
                .setParameter("userId", userId)
                .getResultList();
        if (requestedFriendshipUsers.isEmpty()) return new ArrayList<>();
        return requestedFriendshipUsers;
    }
}
