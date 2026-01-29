package com.nlitvins.social_media.domain.usecase.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentByPostId(Set<Integer> postIds) {
        return commentRepository.findByPostIds(postIds);
    }

    public List<Comment> getCommentByUserId(Set<Integer> userIds) {
        return commentRepository.findByUserIds(userIds);
    }
}
