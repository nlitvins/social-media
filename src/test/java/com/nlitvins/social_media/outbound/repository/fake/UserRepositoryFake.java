package com.nlitvins.social_media.outbound.repository.fake;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserRepositoryFake implements UserRepository {

    private final HashMap<Integer, User> users =  new HashMap<>();

    @Override
    public List<User> findAll() {
        return users
                .values()
                .stream()
                .map(user -> user.toBuilder().build()).toList();
    }

    @Override
    public User findById(int id) {
        User user = users.get(id);
        return Optional.ofNullable(user)
                .map(p -> user.toBuilder().build())
                .orElse(null);
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user.toBuilder().build();
    }

    public void clear(){
        users.clear();
    }
}
