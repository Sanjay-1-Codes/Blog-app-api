package com.sanjay.api.post.comment.controller;

import com.sanjay.api.post.comment.presentation.CommentDto;
import com.sanjay.api.post.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable(value = "postId") String postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.saveComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComemntsOfAPost(@PathVariable(value = "postId") String postId) {
        return new ResponseEntity<>(commentService.getAllCommentsOfAPost(postId), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "commentId") String commentId) {
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "commentId") String commentId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "commentId") String commentId) {
        return new ResponseEntity<>(commentService.deleteCommentById(commentId), HttpStatus.OK);
    }


}
