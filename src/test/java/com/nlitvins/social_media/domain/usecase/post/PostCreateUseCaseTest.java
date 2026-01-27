package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.outbound.repository.fake.PostRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostCreateUseCaseTest {

    private PostCreateUseCase sut;

    private PostRepositoryFake postRepository;

    @BeforeAll
    void setUp() {
        postRepository = new PostRepositoryFake();
        sut = new PostCreateUseCase(postRepository);
    }

    @Test
    void createPost() {
        Post before = postRepository.findById(1);
        assertNull(before);

        Post post = givenPostFirst();
        Post result = sut.addPost(post);
        assertNotNull(result);

        Post savedPost = postRepository.findById(1);
        assertNotNull(savedPost);
    }


    private Post givenPostFirst() {
        return postRepository.save(
                Post.builder().id(1)
                        .authorId(1)
                        .content("TestContent1")
                        .creationTime(LocalDateTime.parse("2025-05-16T23:59:59.999999999"))
                        .build()
        );
    }
}
