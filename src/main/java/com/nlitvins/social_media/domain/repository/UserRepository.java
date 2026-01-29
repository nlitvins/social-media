package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<User> findAll();

    User findById(int id);

    User save(User User);

    List<User> findByIds(Set<Integer> userIds);
}
