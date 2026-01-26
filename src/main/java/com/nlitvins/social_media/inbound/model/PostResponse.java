package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class PostResponse {
    private int id;
    private int authorId;
    private String content;
    private LocalDateTime creationTime;
}
