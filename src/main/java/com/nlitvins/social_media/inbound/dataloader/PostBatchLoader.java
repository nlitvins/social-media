package com.nlitvins.social_media.inbound.dataloader;


import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostReadUseCase;
import org.dataloader.BatchLoaderEnvironment;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class PostBatchLoader {

    public PostBatchLoader(
            BatchLoaderRegistry registry,
            PostReadUseCase postReadUseCase) {
        registry.<Integer, List<Post>>forName("postsByAuthor")
                .registerMappedBatchLoader((Set<Integer> authorIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<Post>> result = new HashMap<>();
                            authorIds.forEach(id -> result.put(id, new ArrayList<>()));

                            postReadUseCase.getPostsByAuthorIds(authorIds)
                                    .forEach(post -> result.get(post.getAuthorId()).add(post));

                            return result;
                        })
                );

        registry.<Integer, List<Post>>forName("postsById")
                .registerMappedBatchLoader((Set<Integer> postsId, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<Post>> result = new HashMap<>();
                            postsId.forEach(id -> result.put(id, new ArrayList<>()));

                            postReadUseCase.getPostsByIds(postsId)
                                    .forEach(post -> result.get(post.getId()).add(post));

                            return result;
                        })
                );
    }


}
