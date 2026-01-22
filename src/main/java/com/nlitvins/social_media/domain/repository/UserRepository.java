package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int id);

    User save(User User);
}
