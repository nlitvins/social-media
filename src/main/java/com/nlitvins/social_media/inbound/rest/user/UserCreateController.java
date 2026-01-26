package com.nlitvins.social_media.inbound.rest.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserCreateUseCase;
import com.nlitvins.social_media.inbound.model.UserCreateRequest;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserCreateController {

    private final UserCreateUseCase userCreateUseCase;

    @MutationMapping
    public UserResponse createUser(@Argument UserCreateRequest request) {
        User user = InboundMapper.Users.toDomain(request);
        User savedUser = userCreateUseCase.addUser(user);
        return InboundMapper.Users.toDTO(savedUser);
    }
}
