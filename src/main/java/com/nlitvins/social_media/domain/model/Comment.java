package com.nlitvins.social_media.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Comment {

    private int id;
    private int authorId;
    private int postId;
    private String content;
    private LocalDateTime creationTime;

}
