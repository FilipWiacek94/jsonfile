package org.json.jsonfile.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.jsonfile.model.Post;
import org.json.jsonfile.service.impl.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private static final String ARCHIVE_PATH = "src/main/resources/archive/";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplate restTemplate;

    public PostServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveAllPosts() {
        ResponseEntity<Post[]> postsResponse =
                restTemplate.getForEntity(POSTS_URL, Post[].class);

        Optional<Post[]> postsOptional = Optional.ofNullable(postsResponse.getBody());
        Arrays.stream(postsOptional.orElseThrow(IllegalArgumentException::new)).forEach(this::createPostDocument);
    }

    private void createPostDocument(Post post) {
        try (Writer writer = new FileWriter(ARCHIVE_PATH + post.getId() + ".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(post, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
