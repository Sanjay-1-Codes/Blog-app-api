package com.sanjay.api.post.presentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String id;

    private String title;

    private String description;

    private String content;


}
