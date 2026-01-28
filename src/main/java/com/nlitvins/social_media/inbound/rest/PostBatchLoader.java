package com.nlitvins.social_media.inbound.rest;


import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
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
            PostRepository postRepository
    ) {
        registry.<Integer, List<Post>>forName("postsByAuthor")
                .registerMappedBatchLoader((Set<Integer> authorIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<Post>> result = new HashMap<>();
                            authorIds.forEach(id -> result.put(id, new ArrayList<>()));

                            postRepository.findByAuthorIdIn(authorIds)
                                    .forEach(post ->
                                            result.get(post.getAuthorId()).add(post)
                                    );

                            return result;
                        })
                );
    }

}
