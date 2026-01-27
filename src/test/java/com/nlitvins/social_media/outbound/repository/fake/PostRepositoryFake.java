package com.nlitvins.social_media.outbound.repository.fake;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryFake implements PostRepository {

    private final HashMap<Integer, Post> posts = new HashMap<>();

    @Override
    public List<Post> findAll() {
        return posts
                .values()
                .stream()
                .map(post -> post.toBuilder().build()).toList();
    }

    @Override
    public Post findById(int id) {
        Post post = posts.get(id);
        return Optional.ofNullable(post)
                .map(p -> post.toBuilder().build())
                .orElse(null);
    }

    @Override
    public Post save(Post post) {
        posts.put(post.getId(), post);
        return post.toBuilder().build();
    }

    public void clear() {
        posts.clear();
    }
}
