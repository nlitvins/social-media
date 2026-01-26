package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CommentRequest {
    private Integer authorId;
    private Integer postId;
    private String content;
    private LocalDateTime creationTime;
}
