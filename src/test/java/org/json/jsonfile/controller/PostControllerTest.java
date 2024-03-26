package org.json.jsonfile.controller;

import org.json.jsonfile.service.impl.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    void saveAllPosts_ShouldReturnOkResponse() {
        //given
        //when
        ResponseEntity<String> response = postController.saveAllPosts();

        //then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Posts was successfully saved");
    }

    @Test
    void saveAllPosts_ShouldCallSaveAllPostsMethodInPostService() {
        //given
        //when
        postController.saveAllPosts();

        //then
        verify(postService, times(1)).saveAllPosts();
    }
}
