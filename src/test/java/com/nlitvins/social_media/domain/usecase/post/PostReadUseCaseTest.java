package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.outbound.repository.fake.PostRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostReadUseCaseTest {

    private PostReadUseCase sut;

    public PostRepositoryFake postRepository;

    @BeforeAll
    void setup() {
        postRepository = new PostRepositoryFake();
        sut = new PostReadUseCase(postRepository);
    }

    @BeforeEach
    void clear() {
        postRepository.clear();
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

    private Post givenPostSecond() {
        return postRepository.save(
                Post.builder().id(2)
                        .authorId(2)
                        .content("TestContent2")
                        .creationTime(LocalDateTime.parse("2025-05-16T23:59:59.999999999"))
                        .build()
        );

    }
}
