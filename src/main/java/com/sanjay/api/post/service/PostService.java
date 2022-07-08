package com.sanjay.api.post.service;

import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.presentation.PostPaginationResponse;

import java.util.List;

public interface PostService {

    PostDto savePost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostPaginationResponse getPostsByPaginationAndSort(int pageNo, int pageSize, String sortBy);

    PostDto getPostById(String id);

    PostDto updatePost(PostDto postDto, String id);

    String deletePostById(String id);
}
