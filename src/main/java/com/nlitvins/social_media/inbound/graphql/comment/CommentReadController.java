package com.nlitvins.social_media.inbound.graphql.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentReadUseCase;
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

import static com.nlitvins.social_media.inbound.dataloader.LoaderNames.COMMENTS_BY_POST;

@Controller
@RequiredArgsConstructor
public class CommentReadController {
    private final CommentReadUseCase commentReadUseCase;

    @QueryMapping
    public List<CommentResponse> comments() {
        List<Comment> comments = commentReadUseCase.getComments();
        return InboundMapper.Comments.toDTOList(comments);
    }

    @QueryMapping
    public CommentResponse getComment(@Argument int id) {
        Comment comment = commentReadUseCase.getCommentById(id);
        return InboundMapper.Comments.toDTO(comment);
    }

    @SchemaMapping(typeName = "Post", field = "comments")
    public CompletableFuture<List<CommentResponse>> comments(
            PostResponse post,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<Comment>> dataLoader = env.getDataLoader(COMMENTS_BY_POST);

        return dataLoader.load(post.getId())
                .thenApply(InboundMapper.Comments::toDTOList);
    }

    @SchemaMapping(typeName = "User", field = "comments")
    public CompletableFuture<List<CommentResponse>> comments(
            UserResponse user,
            DataFetchingEnvironment env
    ) {
        DataLoader<Integer, List<Comment>> dataLoader = env.getDataLoader(COMMENTS_BY_POST);

        return dataLoader.load(user.getId())
                .thenApply(InboundMapper.Comments::toDTOList);
    }
}
