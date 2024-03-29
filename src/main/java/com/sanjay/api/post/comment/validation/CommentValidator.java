package com.sanjay.api.post.comment.validation;

import com.sanjay.api.post.comment.presentation.CommentDto;

import static com.sanjay.api.utils.NullCheckUtils.shouldBeNull;
import static com.sanjay.api.utils.NullCheckUtils.shouldNotBeNullOrEmptyString;

public class CommentValidator {

    private CommentValidator() {}

    public static void validateCommentPayloadForCreate(CommentDto commentDto) {
        validateIdForCreate(commentDto.getId());
        validateName(commentDto.getName());
        validateEmail(commentDto.getEmail());
        validateMessage(commentDto.getMessage());
    }

    public static void validateCommentPayloadForUpdate(CommentDto commentDto) {
        validateIdForUpdate(commentDto.getId());
        validateName(commentDto.getName());
        validateEmail(commentDto.getEmail());
        validateMessage(commentDto.getMessage());
    }

    private static void validateIdForUpdate(String id) { shouldNotBeNullOrEmptyString("Comment Id for update", id);}

    private static void validateIdForCreate(String id) {
        shouldBeNull("Comment Id for create", id);
    }

    private static void validateName(String name) {
        shouldNotBeNullOrEmptyString("Name for comment", name);
    }

    private static void validateEmail(String email) {
        shouldNotBeNullOrEmptyString("Email for comment", email);
    }

    private static void validateMessage(String message) {
        shouldNotBeNullOrEmptyString("Message for comment", message);
    }
}
