package com.sanjay.api.post.comment.service;

import com.sanjay.api.post.comment.presentation.CommentDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(String postId, CommentDto commentDto);

    List<CommentDto> getAllCommentsOfAPost(String postId);

    CommentDto getCommentById(String commentId);
    
    CommentDto updateComment(String commentId, CommentDto commentDto);

    String deleteCommentById(String commentId);
}
