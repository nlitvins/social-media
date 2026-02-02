package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.exception.BusinessException;
import com.nlitvins.social_media.domain.exception.ErrorCode;
import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class PostReadUseCase {

    private final PostRepository postRepository;

    public PostReadUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByAuthorIds(Set<Integer> authorIds) {
        return postRepository.findByAuthorIdIn(authorIds);
    }

    public Post getPostById(int id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND,
                    Map.of("postId", id));
        }
        return post;
    }

    public List<Post> getPostsByIds(Set<Integer> postIds) {
        return postRepository.findByIds(postIds);
    }
}
