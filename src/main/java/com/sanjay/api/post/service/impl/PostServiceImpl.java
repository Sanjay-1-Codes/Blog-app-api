package com.sanjay.api.post.service.impl;

import com.sanjay.api.post.converter.PostConverter;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.exception.IllegalClientArgumentException;
import com.sanjay.api.post.exception.ResourceNotFoundException;
import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.presentation.PostPaginationResponse;
import com.sanjay.api.post.repository.PostRepository;
import com.sanjay.api.post.service.PostService;
import com.sanjay.api.post.validation.PostPayloadValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sanjay.api.utils.AppConstants.POST_ID;
import static com.sanjay.api.utils.NullCheckUtils.shouldNotBeNullOrEmptyString;

//Preferred injection is constructor injection

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private PostConverter postConverter;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostConverter postConverter) {
        this.postRepository = postRepository;
        this.postConverter = postConverter;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        PostPayloadValidator.validatePostPayloadForCreate(postDto);
        Post createdPost = postRepository.save(postConverter.postDtoToDomain(postDto));
        return postConverter.postDomainToDto(createdPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postConverter.postsToPostDtoList(posts);
    }

    @Override
    public PostPaginationResponse getPostsByPaginationAndSort(int pageNo, int pageSize, String sortBy) {
        PostPayloadValidator.validatePageNoPageSizeAndSortBy(pageNo, pageSize, sortBy);
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pageOfPosts = postRepository.findAll(pageable);
        return postConverter.pageOfPostsToPostPaginationResponse(pageOfPosts);
    }

    @Override
    public PostDto getPostById(String postId) {
        shouldNotBeNullOrEmptyString(POST_ID, postId);
        Post post = postRepository.findById(Long.valueOf(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return postConverter.postDomainToDto(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, String id) {
        shouldNotBeNullOrEmptyString(POST_ID, id);
        PostPayloadValidator.validatePostPayloadForUpdate(postDto);
        Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        Post updatedPost = postRepository.save(postConverter.populatePostDomain(postDto,post));
        return postConverter.postDomainToDto(updatedPost);
    }

    @Override
    public String deletePostById(String id) {
        shouldNotBeNullOrEmptyString(POST_ID, id);
        Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);
        return String.format("Post with id : %s deleted successfully", id);
    }
}
