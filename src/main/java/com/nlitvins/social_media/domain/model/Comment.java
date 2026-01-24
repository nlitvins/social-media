package com.nlitvins.social_media.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Comment {

    private Integer id;
    private Integer authorId;
    private Integer postId;
    private String content;
    private LocalDateTime creationTime;

}
