package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.TestcontainersConfiguration;
import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static com.nlitvins.social_media.utils.TimeUtils.roundToMicros;
import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest
class PostRepositoryImplTest {

    @Autowired
    private PostRepository sut;

    @Test
    void findAll() {
        List<Post> all = sut.findAll();

        assertThat(all)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void findById() {
        Post post = sut.findById(1);

        assertThat(post)
                .isNotNull();
    }

    @Test
    void save(){
        Post given = givenPost();

        LocalDateTime start = LocalDateTime.now();
        Post saved = sut.save(given);
        LocalDateTime end = LocalDateTime.now();

        assertThat(saved)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id", "creationTime")
                .isEqualTo(given);

        LocalDateTime savedTime = saved.getCreationTime();
        assertThat(savedTime)
                .isNotNull()
                .isBetween(start, end);

        Post expectedFromDb = saved.toBuilder()
                .creationTime(roundToMicros(saved.getCreationTime()))
                .build();
        Post fromDb = sut.findById(saved.getId());
        assertThat(fromDb)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedFromDb);
    }

    private Post givenPost() {
        return Post.builder()
                .authorId(1)
                .content("TestPost")
                .build();
    }
}
