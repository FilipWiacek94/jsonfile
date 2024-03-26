package org.json.jsonfile.controller;

import org.json.jsonfile.service.impl.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<String> saveAllPosts() {
        postService.saveAllPosts();
        return new ResponseEntity<>("Posts was successfully saved", HttpStatus.OK);
    }
}
