package com.nlitvins.social_media.domain.usecase.post;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.outbound.repository.fake.PostRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostReadUseCaseTest {

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

    @Test
    void emptyListReturnWhenGetPostsCalled(){
        List<Post> result = sut.getPosts();

        assertTrue(result.isEmpty());
    }

    @Test
    void returnListWhenGetPostsCalled(){
        Post post1 = givenPostFirst();
        Post post2 = givenPostSecond();

        List<Post> result = sut.getPosts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(List.of(post1, post2).toArray()));
    }

    @Test
    void returnNullWhenPostNotFound(){
        Post result = sut.getPostById(1);

        assertNull(result);
    }

    @Test
    void returnPostWhenPostCalled(){
        Post post = givenPostFirst();

        Post result = sut.getPostById(1);

        assertNotNull(result);
        assertEquals(post, result);
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
