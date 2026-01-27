package com.nlitvins.social_media.inbound.rest.user;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserReadUseCase;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserReadController {

    private final UserReadUseCase userReadUseCase;

    @QueryMapping
    public List<UserResponse> users() {
        List<User> users = userReadUseCase.getUsers();
        return InboundMapper.Users.toDTOList(users);
    }

    @QueryMapping
    public UserResponse getUser(@Argument int id) {
        User user = userReadUseCase.getUserById(id);
        return InboundMapper.Users.toDTO(user);
    }

}
