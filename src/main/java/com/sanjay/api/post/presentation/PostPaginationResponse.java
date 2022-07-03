package com.sanjay.api.post.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPaginationResponse {

    private List<PostDto> posts;

    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean isLast;



}
