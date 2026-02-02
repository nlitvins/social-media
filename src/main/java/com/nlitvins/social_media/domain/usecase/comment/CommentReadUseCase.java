package com.nlitvins.social_media.domain.usecase.comment;

import com.nlitvins.social_media.domain.exception.BusinessException;
import com.nlitvins.social_media.domain.exception.ErrorCode;
import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CommentReadUseCase {

    private final CommentRepository commentRepository;

    public CommentReadUseCase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            throw new BusinessException(ErrorCode.COMMENT_NOT_FOUND,
                    Map.of("Comment: ", id));
        }
        return comment;
    }

    public List<Comment> getCommentsByPostIds(Set<Integer> postIds) {
        return commentRepository.findByPostIds(postIds);
    }

    public List<Comment> getCommentsByUserIds(Set<Integer> userIds) {
        return commentRepository.findByUserIds(userIds);
    }
}
