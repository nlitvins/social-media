package com.nlitvins.social_media.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Post {
    private int id;
    private int authorId;
    private String content;
    private LocalDateTime creationTime;
}
