package com.nlitvins.social_media.inbound.rest.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentReadUseCase;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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
}
