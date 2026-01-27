package com.nlitvins.social_media.inbound.rest.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.usecase.post.PostReadUseCase;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    @SchemaMapping
    public PostResponse posts(User user) {
        Post post = postReadUseCase.getPostByAuthorId(user.getId());
        return InboundMapper.Posts.toDTO(post);
    }
}
