package com.sanjay.api.post.service.impl;

import com.sanjay.api.post.PostConverter;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.repository.PostRepository;
import com.sanjay.api.post.service.PostService;
import com.sanjay.api.post.validation.PostPayloadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
