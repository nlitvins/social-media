package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@Builder
public class UserResponse {
    private int id;
    private String userName;
}
