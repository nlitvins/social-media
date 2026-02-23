package com.nlitvins.social_media.domain.usecase.user;

import com.nlitvins.social_media.domain.exception.BusinessException;
import com.nlitvins.social_media.domain.exception.ErrorCode;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UserReadUseCase {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND,
                    Map.of("UserId: ", id));
        }
        return user;
    }

    public List<User> getUsersByIds(Set<Integer> userIds) {
        return userRepository.findByIds(userIds);
    }
}
