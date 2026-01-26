package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostCreateUseCase {

    private final PostRepository postRepository;

    public PostCreateUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }
}
