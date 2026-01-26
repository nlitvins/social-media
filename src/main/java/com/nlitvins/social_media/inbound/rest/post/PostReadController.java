package com.nlitvins.social_media.inbound.rest.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostReadUseCase;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class PostReadController {

    private final PostReadUseCase postReadUseCase;

    @QueryMapping
    public List<PostResponse> posts() {
        List<Post> posts = postReadUseCase.getPosts();
        return InboundMapper.Posts.toDTOList(posts);
    }

    @QueryMapping
    public PostResponse getPost(@Argument int id) {
        Post post = postReadUseCase.getPostById(id);
        return InboundMapper.Posts.toDTO(post);
    }
}
