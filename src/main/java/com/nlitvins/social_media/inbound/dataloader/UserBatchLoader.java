package com.nlitvins.social_media.inbound.dataloader;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserReadUseCase;
import org.dataloader.BatchLoaderEnvironment;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserBatchLoader {

    public UserBatchLoader(
            BatchLoaderRegistry registry,
            UserReadUseCase userReadUseCase) {
        registry.<Integer, User>forName("usersById")
                .registerMappedBatchLoader((Set<Integer> userIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {

                            List<User> users = userReadUseCase.getUserByIds(userIds);

                            return users.stream()
                                    .collect(Collectors.toMap(
                                            User::getId,
                                            Function.identity()
                                    ));
                        })
                );
    }
}
