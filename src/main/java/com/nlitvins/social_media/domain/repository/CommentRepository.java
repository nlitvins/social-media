package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll();

    Comment findById(int id);

    Comment save(Comment comment);
}
