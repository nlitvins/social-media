package com.nlitvins.social_media.outbound.repository.fake;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryFake implements CommentRepository {

    private final HashMap<Integer, Comment> comments = new HashMap<>();

    @Override
    public List<Comment> findAll() {
        return comments
                .values()
                .stream()
                .map(comment -> comment.toBuilder().build()).toList();
    }

    @Override
    public Comment findById(int id) {
        Comment comment = comments.get(id);
        return Optional.ofNullable(comment)
                .map(c -> comment.toBuilder().build())
                .orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        comments.put(comment.getId(), comment);
        return comment.toBuilder().build();
    }

    public void clear() {
        comments.clear();
    }
}
