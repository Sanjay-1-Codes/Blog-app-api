package com.sanjay.api.post.controller;

import com.sanjay.api.post.presentation.PostDto;
import com.sanjay.api.post.presentation.PostPaginationResponse;
import com.sanjay.api.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.savePost(postDto), HttpStatus.CREATED);
    }

    /*Pagination and Sorting are required mainly to display is tabular format.
    Get all posts api is altered to have query parameters for pagination and sorting support, query parameters are optional,
    so default values are to be instantiated whenever needed (page num = 0, page size = 10)*/
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PostPaginationResponse> getPostsByPageNoAndPageSize(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(postService.getPostsByPagination(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") String id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") String id) {
        return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.OK);
    }
}
