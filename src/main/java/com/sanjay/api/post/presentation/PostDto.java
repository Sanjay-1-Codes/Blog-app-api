package com.sanjay.api.post.presentation;

import com.sanjay.api.post.comment.presentation.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String id;

    private String title;

    private String description;

    private String content;

    private List<CommentDto> comments;


}
