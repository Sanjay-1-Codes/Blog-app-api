package com.sanjay.api.post.comment.converter;

import com.sanjay.api.post.comment.domain.Comment;
import com.sanjay.api.post.comment.presentation.CommentDto;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.presentation.PostDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CommentConverter {

    public Comment commentDtoToDomain(CommentDto commentDto) {

        if(Objects.nonNull(commentDto)) {
            Comment comment = new Comment();
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());
            comment.setMessage(commentDto.getMessage());
            return  comment;
        }
        return null;
    }

    public CommentDto commentDomainToDto(Comment comment) {

        if(Objects.nonNull(comment)) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(String.valueOf(comment.getId()));
            commentDto.setName(comment.getName());
            commentDto.setEmail(comment.getEmail());
            commentDto.setMessage(comment.getMessage());
            return  commentDto;
        }
        return null;
    }

    public List<CommentDto> commentsToCommentDtoList(List<Comment> comments) {
        if(!CollectionUtils.isEmpty(comments)) {
            return comments.stream().map(post -> commentDomainToDto(post)).collect(Collectors.toList());
        }
        return null;
    }

    public Comment populateCommentDomain(CommentDto source, Comment target) {
        Assert.notNull(source, "Comment Dto object cannot be null");
        Assert.notNull(target, "Comment Domain object cannot be null");
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMessage(source.getMessage());
        return target;
    }

    /*
    Post pagination starts with page 0 and moves on, but the Page<Post> gives current pageNo based on start 0
    and Total pages value starting from 1. To make it uniform as zero based index subtract 1 from the returned value
     */
//    public PostPaginationResponse pageOfPostsToPostPaginationResponse(Page<Post> posts) {
//        return PostPaginationResponse.builder()
//                .posts(postsToPostDtoList(posts.toList()))
//                .pageNo(posts.getNumber())
//                .pageSize(posts.getSize())
//                .totalElements(posts.getTotalElements())
//                .totalPages(posts.getTotalPages() - 1)
//                .isLast(posts.isLast())
//                .build();
//    }
}
