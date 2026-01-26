package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CommentCreateRequest {
    private int authorId;
    private int postId;
    private String content;
    private LocalDateTime creationTime;
}
