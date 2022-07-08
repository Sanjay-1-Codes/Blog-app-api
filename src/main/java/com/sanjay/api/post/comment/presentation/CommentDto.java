package com.sanjay.api.post.comment.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String id;
    private String name;
    private String email;
    private String message;
}
