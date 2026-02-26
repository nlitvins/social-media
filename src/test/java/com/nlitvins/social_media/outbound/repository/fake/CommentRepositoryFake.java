package com.nlitvins.social_media.outbound.repository.fake;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CommentRepositoryFake implements CommentRepository {

    private final HashMap<Integer, Comment> comments = new HashMap<>();

    @Override
    public List<Comment> findAll() {
        return comments
                .values()
                .stream()
                .map(comment -> comment.toBuilder().build())
                .toList();
    }

    @Override
    public Comment findById(int id) {
        Comment comment = comments.get(id);
        return Optional.ofNullable(comment)
                .map(c -> c.toBuilder().build())
                .orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        comments.put(comment.getId(), comment);
        return comment.toBuilder().build();
    }

    @Override
    public List<Comment> findByPostIds(Set<Integer> postIds) {
        return null;
    }

    @Override
    public List<Comment> findByUserIds(Set<Integer> userId) {
        return null;
    }


    public void clear() {
        comments.clear();
    }


}
