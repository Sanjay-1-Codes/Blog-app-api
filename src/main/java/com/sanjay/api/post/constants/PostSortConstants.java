package com.sanjay.api.post.constants;

import java.util.HashMap;
import java.util.Map;

public enum PostSortConstants {

    ID("id"),
    TITLE("title"),
    DESCRIPTION("description"),
    CONTENT("content");

    private final String value;

    private static final Map<String, PostSortConstants> postSortConstantsMap = new HashMap<>();

    static {
        for(PostSortConstants constant : values()) {
            postSortConstantsMap.put(constant.value, constant);
        }
    }

    PostSortConstants(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PostSortConstants from(String value) { return postSortConstantsMap.get(value); }

}
