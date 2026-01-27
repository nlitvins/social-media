package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();

    Post findById(int id);

    Post save(Post Post);

    Post findByAuthorId(int authorId);
}
