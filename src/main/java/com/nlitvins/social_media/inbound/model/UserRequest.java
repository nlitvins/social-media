package com.nlitvins.social_media.inbound.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRequest {
    private String userName;
}
