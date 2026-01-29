package com.nlitvins.social_media.inbound.graphql.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentCreateUseCase;
import com.nlitvins.social_media.inbound.model.CommentCreateRequest;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CommentCreateController {

    private final CommentCreateUseCase commentCreateUseCase;

    @MutationMapping
    public CommentResponse createComment(@Argument CommentCreateRequest request) {
        Comment comment = InboundMapper.Comments.toDomain(request);
        Comment savedComment = commentCreateUseCase.addComment(comment);
        return InboundMapper.Comments.toDTO(savedComment);
    }
}
