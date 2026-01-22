package com.nlitvins.social_media.outbound.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author_id", nullable = false)
    private int authorId;

    @Column(name = "post_id", nullable = false)
    private int postId;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @Column(name = "creation_time", nullable = false)
    @JsonIgnore
    @CreatedDate
    private LocalDateTime creationTime;

}
