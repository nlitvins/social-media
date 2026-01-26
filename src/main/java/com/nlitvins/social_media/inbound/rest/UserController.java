package com.nlitvins.social_media.inbound.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    @QueryMapping
    String test() {
        return "success";
    }
}
