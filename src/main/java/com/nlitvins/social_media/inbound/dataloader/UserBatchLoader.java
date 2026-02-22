package com.nlitvins.social_media.inbound.dataloader;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.user.UserReadUseCase;
import org.dataloader.BatchLoaderEnvironment;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.nlitvins.social_media.inbound.dataloader.LoaderNames.USER_BY_ID;

@Component
public class UserBatchLoader {

    public UserBatchLoader(
            BatchLoaderRegistry registry,
            UserReadUseCase userReadUseCase) {
        registry.<Integer, List<User>> forName(USER_BY_ID)
                .registerMappedBatchLoader((Set<Integer> userIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<User>> result = new HashMap<>();
                            userIds.forEach(id -> result.put(id, new ArrayList<>()));

                            userReadUseCase.getUsersByIds(userIds)
                                    .forEach(user -> result.get(user.getId()).add(user));

                            return result;
                        })
                );
    }
}
