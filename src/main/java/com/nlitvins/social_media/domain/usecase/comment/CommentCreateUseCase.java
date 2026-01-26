package com.nlitvins.social_media.domain.usecase.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentCreateUseCase {

    private final CommentRepository commentRepository;

    public CommentCreateUseCase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
