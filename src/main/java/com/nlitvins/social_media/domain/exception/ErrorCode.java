package com.nlitvins.social_media.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    POST_NOT_FOUND("Post not found"),
    USER_NOT_FOUND("User not found"),
    COMMENT_NOT_FOUND("Comment not found"),
    INTERNAL_ERROR("Internal server error");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
