package com.nlitvins.social_media.domain.usecase.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReadUseCase {

    private final UserRepository userRepository;

    public UserReadUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id);
    }
}
