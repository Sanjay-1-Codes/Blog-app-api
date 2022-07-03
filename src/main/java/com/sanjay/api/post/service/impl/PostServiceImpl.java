package com.sanjay.api.post.service.impl;

import com.sanjay.api.post.PostConverter;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.exception.IllegalClientArgumentException;
import com.sanjay.api.post.exception.ResourceNotFoundException;
import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.repository.PostRepository;
import com.sanjay.api.post.service.PostService;
import com.sanjay.api.post.validation.PostPayloadValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    public PostDto getPostById(String id) {
        if(!StringUtils.isEmpty(id)) {
            Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
            return postConverter.postDomainToDto(post);
        }
        throw new IllegalClientArgumentException("Id must not be null/empty");
    }

    @Override
    public PostDto updatePost(PostDto postDto, String id) {
        PostPayloadValidator.validatePostPayloadForUpdate(postDto);
        Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        Post updatedPost = postRepository.save(postConverter.populatePostDomain(postDto,post));
        return postConverter.postDomainToDto(updatedPost);
    }

    @Override
    public String deletePostById(String id) {
        if(!StringUtils.isEmpty(id)) {
            Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
            postRepository.delete(post);
            return String.format("Post with id : %s deleted successfully", id);
        }
        throw new IllegalClientArgumentException("Id must not be null/empty");
    }
}
