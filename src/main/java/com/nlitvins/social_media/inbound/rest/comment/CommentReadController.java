package com.nlitvins.social_media.inbound.rest.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.usecase.comment.CommentReadUseCase;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.utils.InboundMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/comments", produces = APPLICATION_JSON_VALUE)
public class CommentReadController {
    private final CommentReadUseCase commentReadUseCase;

    public CommentReadController(CommentReadUseCase commentReadUseCase) {
        this.commentReadUseCase = commentReadUseCase;
    }

    @GetMapping
    public List<CommentResponse> comments() {
        List<Comment> comments = commentReadUseCase.getComments();
        return InboundMapper.Comments.toDTOList(comments);
    }

    @GetMapping("/{commentId}")
    public CommentResponse findComment(@PathVariable int commentId) {
        Comment comment = commentReadUseCase.getCommentById(commentId);
        return InboundMapper.Comments.toDTO(comment);
    }
}
