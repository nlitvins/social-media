package com.nlitvins.social_media.inbound.utils;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostsByAuthorDataLoader {

    private final PostRepository postRepository;

    public BatchLoader<Integer, List<Post>> batchLoader() {
        return authorIds -> CompletableFuture.supplyAsync(() -> {

            List<Post> posts = postRepository.findByAuthorIdIn(authorIds);

            Map<Integer, List<Post>> postsByAuthor = posts.stream()
                    .collect(Collectors.groupingBy(Post::getAuthorId));

            return authorIds.stream()
                    .map(id -> postsByAuthor.getOrDefault(id, Collections.emptyList()))
                    .toList();
        });
    }
}
