package com.nlitvins.social_media.inbound.rest.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostCreateUseCase;
import com.nlitvins.social_media.inbound.model.PostCreateRequest;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostCreateController {

    private final PostCreateUseCase postCreateUseCase;

    @MutationMapping
    public PostResponse createPost(@Argument PostCreateRequest request) {
        Post post = InboundMapper.Posts.toDomain(request);
        Post savedPost = postCreateUseCase.addPost(post);
        return InboundMapper.Posts.toDTO(savedPost);
    }
}
