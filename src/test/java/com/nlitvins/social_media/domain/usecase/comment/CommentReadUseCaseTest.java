package com.nlitvins.social_media.domain.usecase.comment;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.outbound.repository.fake.CommentRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.List;

import static graphql.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentReadUseCaseTest {

    private CommentReadUseCase sut;
    private CommentRepositoryFake  commentRepository;

    @BeforeAll
    void setup() {
        commentRepository = new CommentRepositoryFake();
        sut = new CommentReadUseCase(commentRepository);
    }

    @BeforeEach
    void clear(){
        commentRepository.clear();
    }

    @Test
    void returnEmptyListWhenNoCommentsExist(){
        List<Comment> result = commentRepository.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void returnListWhenCommentsExist(){
        Comment comment1 = givenCommentFirst();
        Comment comment2 = givenCommentSecond();

        List<Comment> result = sut.getComments();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(List.of(comment1, comment2).toArray()));
    }

    @Test
    void returnCommentWhenCommentExist(){
        Comment comment1 = givenCommentFirst();

        Comment result = sut.getCommentById(1);

        assertNotNull(result);
        assertEquals(comment1, result);
    }

    @Test
    void returnNullWhenCommentNotExist(){
        Comment result = sut.getCommentById(1);

        assertNull(result);
    }


    private Comment givenCommentFirst() {
        return commentRepository.save(Comment.builder()
                .id(1)
                .authorId(1)
                .postId(1)
                .content("TestContent1")
                .creationTime(LocalDateTime.parse("2025-05-16T23:59:59.999999999"))
                .build()
        );
    }

    private Comment givenCommentSecond() {
        return commentRepository.save(Comment.builder()
                .id(2)
                .authorId(2)
                .postId(2)
                .content("TestContent2")
                .creationTime(LocalDateTime.parse("2025-05-16T23:59:59.999999999"))
                .build()
        );
    }
}
