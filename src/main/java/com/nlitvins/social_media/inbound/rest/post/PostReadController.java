package com.nlitvins.social_media.inbound.rest.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostReadUseCase;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostReadController {

    private final PostReadUseCase postReadUseCase;

    public PostReadController(PostReadUseCase postReadUseCase) {
        this.postReadUseCase = postReadUseCase;
    }

    @GetMapping
    public List<PostResponse> post() {
        List<Post> posts = postReadUseCase.getPosts();
        return InboundMapper.Posts.toDTOList(posts);
    }

    @GetMapping("/{postId}")
    public PostResponse findPost(@PathVariable int postId) {
        Post post = postReadUseCase.getPostById(postId);
        return InboundMapper.Posts.toDTO(post);
    }
}
