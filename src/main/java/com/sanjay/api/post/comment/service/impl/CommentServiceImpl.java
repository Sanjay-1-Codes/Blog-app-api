package com.sanjay.api.post.comment.service.impl;

import com.sanjay.api.post.comment.converter.CommentConverter;
import com.sanjay.api.post.comment.domain.Comment;
import com.sanjay.api.post.comment.presentation.CommentDto;
import com.sanjay.api.post.comment.repository.CommentRepository;
import com.sanjay.api.post.comment.service.CommentService;
import com.sanjay.api.post.comment.validation.CommentValidator;
import com.sanjay.api.post.domain.Post;
import com.sanjay.api.post.exception.ResourceNotFoundException;
import com.sanjay.api.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.sanjay.api.utils.AppConstants.COMMENT;
import static com.sanjay.api.utils.AppConstants.COMMENT_ID;
import static com.sanjay.api.utils.AppConstants.POST_ID;
import static com.sanjay.api.utils.NullCheckUtils.shouldNotBeNullOrEmptyString;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentConverter commentConverter, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto saveComment(String postId, CommentDto commentDto) {
        shouldNotBeNullOrEmptyString(POST_ID, postId);
        CommentValidator.validateCommentPayloadForCreate(commentDto);
        Comment comment = commentConverter.commentDtoToDomain(commentDto);
        Post post = postRepository.findById(Long.valueOf(postId))
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        return commentConverter.commentDomainToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllCommentsOfAPost(String postId) {
        shouldNotBeNullOrEmptyString(POST_ID, postId);
        List<Comment> comments = commentRepository.findByPostId(Long.valueOf(postId));
        if (CollectionUtils.isEmpty(comments)) {
            throw new ResourceNotFoundException("Post/Comment for the respective post", "Id", postId);
        }
        return commentConverter.commentsToCommentDtoList(comments);
    }

    @Override
    public CommentDto getCommentById(String commentId) {
        shouldNotBeNullOrEmptyString(COMMENT_ID, commentId);
        Comment comment = commentRepository.findById(Long.valueOf(commentId))
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, "Id", commentId));
        return commentConverter.commentDomainToDto(comment);

    }

    @Override
    public CommentDto updateComment(String commentId, CommentDto commentDto) {
        shouldNotBeNullOrEmptyString(COMMENT_ID, commentId);
        CommentValidator.validateCommentPayloadForUpdate(commentDto);
        Comment comment = commentRepository.findById(Long.valueOf(commentId))
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, "Id", commentId));
        commentConverter.populateCommentDomain(commentDto, comment);
        Comment updatedComment = commentRepository.save(comment);
        return commentConverter.commentDomainToDto(updatedComment);
    }
    
    public String deleteCommentById(String commentId) {
        shouldNotBeNullOrEmptyString(COMMENT_ID, commentId);
        Comment comment = commentRepository.findById(Long.valueOf(commentId))
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, "Id", commentId));
        commentRepository.delete(comment);
        return String.format("Post with id : %s deleted successfully", commentId);
    }
}
