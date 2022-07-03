package com.sanjay.api.post.validation;

import com.sanjay.api.post.exception.IllegalClientArgumentException;
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

    public static void validatePageNoAndPageSize(int pageNo, int pageSize) {
        validatePageNo(pageNo);
        validatePageSize(pageSize);
    }

    private static void validatePageSize(int pageSize) {
        shouldBeGreaterThanZero("Page size", pageSize);
    }

    private static void validatePageNo(int pageNo) {
        shouldBeGreaterThanOrEqualToZero("Page number", pageNo);
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
            throw new IllegalClientArgumentException(message + " must not be null");
        }
    }

    private static void shouldBeNull(String message, Object object) {
        if(Objects.nonNull(object)) {
            throw new IllegalClientArgumentException(message + " must be null");
        }
    }

    private static void shouldBeGreaterThanOrEqualToZero(String message, int number) {
        if(number < 0) {
            throw new IllegalClientArgumentException(message + " must be greater than equal to zero ");
        }
    }

    private static void shouldBeGreaterThanZero(String message, int number) {
        if(number <= 0) {
            throw new IllegalClientArgumentException(message + " must be greater than zero ");
        }
    }
}
