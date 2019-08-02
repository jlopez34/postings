package com.segware.postings.controller;


import com.segware.postings.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postings")
public class PostingsController {

    public static final Logger logger = LoggerFactory.getLogger(PostingsController.class);

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<List<Post>> getPosting() {
        logger.info("Consuming getPosting");
        List<Post> posts = null;
        System.out.println("All postings");
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Post> getPostById(@PathVariable String id) {
        logger.info("Consuming getPostById");
        Post post = new Post();
        post.setId(Integer.parseInt(id));
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }


    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createPost() {
        logger.info("Consuming createPost");
        Post post = new Post();
        System.out.println("CreatePost:  " + post.toString());
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long id) {
        logger.info("Consuming updatePost");
        Post updatedPost = new Post();
        //Post updatedPost = service.update(id, post);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedPost);
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> deletePost(@PathVariable Long id) {
        logger.info("Consuming deletePost");
        return ResponseEntity.noContent().build();
    }
}
