package com.nlitvins.social_media.inbound.graphql.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserReadUseCase;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.nlitvins.social_media.inbound.dataloader.LoaderNames.USERS_BY_ID;

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

    @SchemaMapping(typeName = "Post", field = "author")
    public CompletableFuture <UserResponse> users(
            PostResponse post,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<User>> dataLoader = env.getDataLoader(USERS_BY_ID);

        return dataLoader.load(post.getAuthorId())
                .thenApply(List::getFirst)
                .thenApply(InboundMapper.Users::toDTO);
    }

    @SchemaMapping(typeName = "Comment", field = "author")
    public CompletableFuture <UserResponse> users(
            CommentResponse comment,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<User>> dataLoader = env.getDataLoader(USERS_BY_ID);

        return dataLoader.load(comment.getAuthorId())
                .thenApply(List::getFirst)
                .thenApply(InboundMapper.Users::toDTO);
    }
}
