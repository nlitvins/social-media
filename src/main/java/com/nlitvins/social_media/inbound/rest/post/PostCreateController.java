package com.nlitvins.social_media.inbound.rest.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostCreateUseCase;
import com.nlitvins.social_media.inbound.model.PostRequest;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostCreateController {

    private final PostCreateUseCase postCreateUseCase;

    public PostCreateController(PostCreateUseCase postCreateUseCase) {
        this.postCreateUseCase = postCreateUseCase;
    }

    @PostMapping
    public PostResponse postPost(@RequestBody PostRequest request) {
        Post post = InboundMapper.Posts.toDomain(request);
        Post savedPost = postCreateUseCase.addPost(post);
        return InboundMapper.Posts.toDTO(savedPost);
    }
}
