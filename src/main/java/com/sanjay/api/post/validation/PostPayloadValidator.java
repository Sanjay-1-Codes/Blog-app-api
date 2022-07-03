package com.sanjay.api.post.validation;

import com.sanjay.api.post.presentation.PostDto;

import java.util.Objects;

public class PostPayloadValidator {

    public static void validatePostPayloadForCreate(PostDto postDto) {
        validateId(postDto.getId());
        validateTitle(postDto.getTitle());
        validateDescription(postDto.getDescription());
        validateContent(postDto.getContent());
    }

    private static void validateId(String id) {
        shouldBeNull("Id for post during create", id);
    }

    private static void validateContent(String content) {
        shouldNotBeNull("Content for post", content);
    }

    private static void validateDescription(String description) {
        shouldNotBeNull("Description for post", description);
    }

    private static void validateTitle(String title) {
        shouldNotBeNull("title for post", title);
    }

    public static void shouldNotBeNull(String message, Object object) {
        if(Objects.isNull(object)) {
            throw new IllegalArgumentException(message + " must not be null");
        }
    }

    public static void shouldBeNull(String message, Object object) {
        if(Objects.nonNull(object)) {
            throw new IllegalArgumentException(message + " must be null");
        }
    }
}
