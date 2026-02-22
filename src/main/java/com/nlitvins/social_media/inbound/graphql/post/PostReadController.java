package com.nlitvins.social_media.inbound.graphql.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.usecase.post.PostReadUseCase;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.nlitvins.social_media.inbound.dataloader.LoaderNames.POST_BY_AUTHOR;
import static com.nlitvins.social_media.inbound.dataloader.LoaderNames.POST_BY_ID;

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

    @SchemaMapping(typeName = "User", field = "posts")
    public CompletableFuture<List<PostResponse>> posts(
            UserResponse user,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<Post>> dataLoader = env.getDataLoader(POST_BY_AUTHOR);

        return dataLoader.load(user.getId())
                .thenApply(InboundMapper.Posts::toDTOList);
    }

    @SchemaMapping(typeName = "Comment", field = "post")
    public CompletableFuture <PostResponse> posts(
            CommentResponse comment,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<Post>> dataLoader = env.getDataLoader(POST_BY_ID);

        return dataLoader.load(comment.getPostId())
                .thenApply(List::getFirst)
                .thenApply(InboundMapper.Posts::toDTO);
    }
}


