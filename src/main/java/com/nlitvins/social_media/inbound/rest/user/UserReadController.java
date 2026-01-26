package com.nlitvins.social_media.inbound.rest.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserReadUseCase;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserReadController {

    private final UserReadUseCase userReadUseCase;

    public UserReadController(UserReadUseCase userReadUseCase) {
        this.userReadUseCase = userReadUseCase;
    }

    @GetMapping
    public List<UserResponse> users() {
        List<User> users = userReadUseCase.getUsers();
        return InboundMapper.Users.toDTOList(users);
    }

    @GetMapping("/{userId}")
    public UserResponse findUser(@PathVariable int userId) {
        User user = userReadUseCase.getUserById(userId);
        return InboundMapper.Users.toDTO(user);
    }

}
