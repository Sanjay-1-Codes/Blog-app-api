package com.sanjay.api.utils;

import com.sanjay.api.post.exception.IllegalClientArgumentException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class NullCheckUtils {

    public static void shouldNotBeNull(String message, Object object) {
        if(Objects.isNull(object)) {
            throw new IllegalClientArgumentException(message + " must not be null");
        }
    }

    public static void shouldBeNull(String message, Object object) {
        if(Objects.nonNull(object)) {
            throw new IllegalClientArgumentException(message + " must be null");
        }
    }

    public static void shouldNotBeNullOrEmptyString(String message, String target) {
        if(StringUtils.isEmpty(target)) {
            throw new IllegalClientArgumentException(message + " must not be null/empty");
        }
    }

}
