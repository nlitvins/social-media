package com.nlitvins.social_media.domain.repository;

import com.nlitvins.social_media.domain.model.Post;

import java.util.List;
import java.util.Set;

public interface PostRepository {

    List<Post> findAll();

    Post findById(int id);

    Post save(Post post);

    List<Post> findByAuthorIdIn(Set<Integer> authorId);

    List<Post> findByIds(Set<Integer> postIds);
}
