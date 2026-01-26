package com.nlitvins.social_media.inbound.rest.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentCreateUseCase;
import com.nlitvins.social_media.inbound.model.CommentRequest;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/comments", produces = APPLICATION_JSON_VALUE)
public class CommentCreateController {

    private final CommentCreateUseCase commentCreateUseCase;

    public CommentCreateController(CommentCreateUseCase commentCreateUseCase) {
        this.commentCreateUseCase = commentCreateUseCase;
    }

    @PostMapping
    public CommentResponse postComment(@RequestBody CommentRequest request) {
        Comment comment = InboundMapper.Comments.toDomain(request);
        Comment savedComment = commentCreateUseCase.addComment(comment);
        return InboundMapper.Comments.toDTO(savedComment);
    }
}
