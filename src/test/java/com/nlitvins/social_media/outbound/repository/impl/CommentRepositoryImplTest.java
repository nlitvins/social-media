package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.TestcontainersConfiguration;
import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
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
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository sut;

    @Test
    void findAll() {
        List<Comment> all = sut.findAll();

        assertThat(all)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void findById() {
        Comment comment = sut.findById(1);

        assertThat(comment)
                .isNotNull();
    }

    @Test
    void findNullById(){
        Comment comment = sut.findById(888);

        assertThat(comment)
                .isNull();
    }

    @Test
    void save() {
        Comment given = givenComment();

        LocalDateTime start = LocalDateTime.now();
        Comment saved = sut.save(given);
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

        Comment expectedFromDb = saved.toBuilder()
                .creationTime(roundToMicros(saved.getCreationTime()))
                .build();
        Comment fromDb = sut.findById(saved.getId());
        assertThat(fromDb)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedFromDb);
    }


    private Comment givenComment() {
        return Comment.builder()
                .authorId(2)
                .postId(3)
                .content("TestContent")
                .build();
    }
}