package com.nlitvins.social_media.inbound.rest.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserCreateUseCase;
import com.nlitvins.social_media.inbound.model.UserRequest;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserCreateController {

    private final UserCreateUseCase userCreateUseCase;

    public UserCreateController(UserCreateUseCase userCreateUseCase) {
        this.userCreateUseCase = userCreateUseCase;
    }

    @PostMapping
    public UserResponse postUser(@RequestBody UserRequest request) {
        User user = InboundMapper.Users.toDomain(request);
        User savedUser = userCreateUseCase.addUser(user);
        return InboundMapper.Users.toDTO(savedUser);
    }
}
