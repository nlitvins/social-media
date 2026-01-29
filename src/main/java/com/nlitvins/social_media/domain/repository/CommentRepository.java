package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.Comment;

import java.util.List;
import java.util.Set;

public interface CommentRepository {

    List<Comment> findAll();

    Comment findById(int id);

    Comment save(Comment comment);

    List<Comment> findByPostIds(Set<Integer> postIds);

    List<Comment> findByUserIds(Set<Integer> userId);
}
