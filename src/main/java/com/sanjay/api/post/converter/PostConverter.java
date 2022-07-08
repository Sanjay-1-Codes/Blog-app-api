package com.sanjay.api.post.converter;

import com.sanjay.api.post.comment.converter.CommentConverter;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.presentation.PostPaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class PostConverter {

    private CommentConverter commentConverter;

    @Autowired
    public PostConverter(CommentConverter commentConverter) {
        this.commentConverter = commentConverter;
    }

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
            postDto.setComments(commentConverter.commentsToCommentDtoList(post.getComments()));
            return  postDto;
        }
        return null;
    }

    public List<PostDto> postsToPostDtoList(List<Post> posts) {
        if(!CollectionUtils.isEmpty(posts)) {
            return posts.stream().map(post -> postDomainToDto(post)).toList();
        }
        return null;
    }

    public Post populatePostDomain(PostDto source, Post target) {
        Assert.notNull(source, "Post Dto object cannot be null");
        Assert.notNull(target, "Post Domain object cannot be null");
        target.setDescription(source.getDescription());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        return target;
    }

    /*
    Post pagination starts with page 0 and moves on, but the Page<Post> gives current pageNo based on start 0
    and Total pages value starting from 1. To make it uniform as zero based index subtract 1 from the returned value
     */
    public PostPaginationResponse pageOfPostsToPostPaginationResponse(Page<Post> posts) {
        return PostPaginationResponse.builder()
                .posts(postsToPostDtoList(posts.toList()))
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages() - 1)
                .isLast(posts.isLast())
                .build();
    }
}
