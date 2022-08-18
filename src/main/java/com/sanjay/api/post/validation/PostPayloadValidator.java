package com.sanjay.api.post.validation;

import com.sanjay.api.post.constants.PostSortConstants;
import com.sanjay.api.post.exception.IllegalClientArgumentException;
import com.sanjay.api.post.presentation.PostDto;

import java.util.Arrays;

import static com.sanjay.api.utils.NullCheckUtils.shouldBeNull;
import static com.sanjay.api.utils.NullCheckUtils.shouldNotBeNull;
import static com.sanjay.api.utils.NullCheckUtils.shouldNotBeNullOrEmptyString;

public class PostPayloadValidator {

    private PostPayloadValidator() {}

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

    public static void validatePageNoPageSizeAndSortBy(int pageNo, int pageSize, String sortBy) {
        validatePageNo(pageNo);
        validatePageSize(pageSize);
        validateSortBy(sortBy);
    }

    private static void validateSortBy(String sortBy) {
        shouldBeInPredefinedEnum("Sort by query parameter",sortBy);
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
        shouldNotBeNullOrEmptyString("Content for post", content);
    }

    private static void validateDescription(String description) {
        shouldNotBeNullOrEmptyString("Description for post", description);
    }

    private static void validateTitle(String title) {
        shouldNotBeNullOrEmptyString("title for post", title);
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

    private static void shouldBeInPredefinedEnum(String message, String target) {
        boolean notContains = Arrays.stream(PostSortConstants.values())
                           .filter(enumValue -> enumValue.getValue().equalsIgnoreCase(target))
                           .findFirst().isEmpty();
        if(notContains) {
            throw new IllegalClientArgumentException(message + " should be in the predefined list");
        }

    }
}
