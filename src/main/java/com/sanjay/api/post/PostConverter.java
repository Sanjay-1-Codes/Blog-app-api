package com.sanjay.api.post;

import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.presentation.PostDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostConverter {

    public Post postDtoToDomain(PostDto postDto) {

        if(Objects.nonNull(postDto)) {
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
            return  post;
        }
        return null;
    }

    public PostDto postDomainToDto(Post post) {

        if(Objects.nonNull(post)) {
            PostDto postDto = new PostDto();
            postDto.setId(String.valueOf(post.getId()));
            postDto.setTitle(post.getTitle());
            postDto.setContent(post.getContent());
            postDto.setDescription(post.getDescription());
            return  postDto;
        }
        return null;
    }
}
