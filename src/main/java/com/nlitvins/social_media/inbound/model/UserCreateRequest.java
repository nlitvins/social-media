package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserCreateRequest {
    private String userName;
}
