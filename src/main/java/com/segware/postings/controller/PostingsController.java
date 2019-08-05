package com.segware.postings.controller;


import com.segware.postings.model.Post;
import com.segware.postings.service.PostingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.*;

@RestController
@RequestMapping(path = "/postings")
public class PostingsController {

    public static final Logger logger = LoggerFactory.getLogger(PostingsController.class);

    @Autowired
    private PostingsService service;

    public PostingsController(PostingsService service) {
        this.service = service;
    }

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<List<Post>> getPosting() {
        logger.info("Consuming getPosting");
        List<Post> posts = service.getAllPost();
        System.out.println("All postings");
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Post> getPostById(@PathVariable String id) {
        logger.info("Consuming getPostById");
        Post post = service.getPostById(Long.parseLong(id));
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }


    @PostMapping("/")
    public @ResponseBody
    ResponseEntity<String> createPost(@RequestBody Post newPost) {
        logger.info("Consuming createPost");
        HttpStatus status = null;
        int postCreated = service.createPost(newPost);
        if (postCreated == 0) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.CREATED;
        }

        Post post = newPost;
        System.out.println("CreatePost:  " + post.toString());
        return new ResponseEntity<String>("POST Response " + status.toString(), status);
    }

    @PutMapping("/")
    public @ResponseBody
    ResponseEntity<Post> updatePost(@RequestBody Post post) {
        logger.info("Consuming updatePost");
        int updatedPost = service.updatePost(post);
        if (updatedPost == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Object> deletePost(@PathVariable Long id) {
        logger.info("Consuming deletePost");
        int postRemoved = service.deletePost(id);
        if (postRemoved == 0) {
            logger.info("Problem revoming post");
        } else {
            logger.info("Post revomed");
        }
        return ResponseEntity.noContent().build();
    }
}
