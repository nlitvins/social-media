package com.nlitvins.social_media.domain.usecase.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCreateUseCase {

    private final UserRepository userRepository;

    public UserCreateUseCase(UserRepository userRepository){this.userRepository = userRepository;}

    public User addUser(User user){
        return userRepository.save(user);
    }
}
