package com.sanjay.api.post.validation;

import com.sanjay.api.post.presentation.PostDto;

import java.util.Objects;

public class PostPayloadValidator {

    public static void validatePostPayloadForCreate(PostDto postDto) {
        validateIdForCreate(postDto.getId());
        validateTitle(postDto.getTitle());
        validateDescription(postDto.getDescription());
        validateContent(postDto.getContent());
    }

    public static void validatePostPayloadForUpdate(PostDto postDto) {
        validateIdForUpdate(postDto.getId());
        validateTitle(postDto.getTitle());
        validateDescription(postDto.getDescription());
        validateContent(postDto.getContent());
    }

    private static void validateIdForUpdate(String id) {
        shouldNotBeNull("Id for post during create", id);
    }

    private static void validateIdForCreate(String id) {
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

    private static void shouldNotBeNull(String message, Object object) {
        if(Objects.isNull(object)) {
            throw new IllegalArgumentException(message + " must not be null");
        }
    }

    private static void shouldBeNull(String message, Object object) {
        if(Objects.nonNull(object)) {
            throw new IllegalArgumentException(message + " must be null");
        }
    }
}
