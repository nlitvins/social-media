package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.PostRepository;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostReadUseCase {

    private final PostRepository postRepository;

    public PostReadUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostByAuthorId(int authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public Post posts(User user){
        return postRepository.findByAuthorId(user.getId());
    }
}
