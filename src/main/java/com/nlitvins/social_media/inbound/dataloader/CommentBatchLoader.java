package com.nlitvins.social_media.inbound.dataloader;


import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentReadUseCase;
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
public class CommentBatchLoader {

    public CommentBatchLoader(
            BatchLoaderRegistry registry,
            CommentReadUseCase commentReadUseCase) {
        registry.<Integer, List<Comment>>forName("commentsByPost")
                .registerMappedBatchLoader((Set<Integer> postsIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<Comment>> result = new HashMap<>();
                            postsIds.forEach(id -> result.put(id, new ArrayList<>()));

                            commentReadUseCase.getCommentsByPostIds(postsIds)
                                    .forEach(comment -> result.get(comment.getPostId()).add(comment));

                            return result;
                        })
                );
        registry.<Integer, List<Comment>>forName("commentsByUser")
                .registerMappedBatchLoader((Set<Integer> usersIds, BatchLoaderEnvironment env) ->
                        Mono.fromSupplier(() -> {
                            Map<Integer, List<Comment>> result = new HashMap<>();
                            usersIds.forEach(id -> result.put(id, new ArrayList<>()));

                            commentReadUseCase.getCommentsByUserIds(usersIds)
                                    .forEach(comment -> result.get(comment.getAuthorId()).add(comment));

                            return result;
                        })
                );
    }
}
