package com.nlitvins.social_media.domain.usecase.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.outbound.repository.fake.CommentRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentCreateUseCaseTest {

    private CommentCreateUseCase sut;
    private CommentRepositoryFake commentRepository;

    @BeforeAll
    void setup() {
        commentRepository = new CommentRepositoryFake();
        sut = new CommentCreateUseCase(commentRepository);
    }

    @BeforeEach
    void clear() {
        commentRepository.clear();
    }

    @Test
    void createComment() {
        Comment before = commentRepository.findById(1);
        assertNull(before);

        Comment comment = commentRepository.save(givenCommentFirst());
        Comment result = sut.addComment(comment);
        assertNotNull(result);

        Comment savedComment = commentRepository.findById(1);
        assertNotNull(savedComment);
    }


    private Comment givenCommentFirst() {
        return commentRepository.save(Comment.builder()
                .id(1)
                .authorId(1)
                .postId(1)
                .content("TestContent")
                .creationTime(LocalDateTime.parse("2025-05-16T23:59:59.999999999"))
                .build()
        );
    }
}
