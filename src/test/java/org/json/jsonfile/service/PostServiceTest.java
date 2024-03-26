package org.json.jsonfile.service;

import org.json.jsonfile.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {
    private static final String ARCHIVE_PATH = "src/main/resources/archive/";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    private PostServiceImpl postService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        postService = new PostServiceImpl(restTemplate);
    }

    @Test
    void testCreatingPostFile() {
        PostServiceImpl postService = new PostServiceImpl(new RestTemplate());
        postService.saveAllPosts();
    }


    @Test
    void saveAllPostsTest() throws IOException {
        //given
        Post[] posts = {new Post(1L, "test", "test", 2L)};
        ResponseEntity<Post[]> responseEntity = new ResponseEntity<>(posts, HttpStatus.OK);
        when(restTemplate.getForEntity(POSTS_URL, Post[].class)).thenReturn(responseEntity);

        //when
        postService.saveAllPosts();

        //then
        File file = new File(ARCHIVE_PATH + "1.json");
        assertTrue(file.exists());

        // Cleaning up
        Files.deleteIfExists(Paths.get(ARCHIVE_PATH + "1.json"));
    }
}
