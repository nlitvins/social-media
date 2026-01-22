package com.nlitvins.social_media.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User {

    private int id;
    private String userName;
}
