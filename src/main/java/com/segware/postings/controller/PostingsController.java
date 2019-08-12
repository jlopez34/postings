package com.segware.postings.controller;


import com.segware.postings.model.Post;
import com.segware.postings.service.PostingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.*;

/**
 * PostingsController class
 * @author Jaime Lopez
 * @Since 1.0.0
 */
@RestController
@RequestMapping(path = "/postings")
@Api(value = "Postings microservice", description = "This API is used to manager posting")
public class PostingsController {

    public static final Logger logger = LoggerFactory.getLogger(PostingsController.class);

    @Autowired
    private PostingsService service;

    public PostingsController(PostingsService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ApiOperation(value = "Find All Postings", notes = "Return every postings registered" )
    public @ResponseBody
    ResponseEntity<List<Post>> getPosting() {
        logger.info("Consuming getPosting");
        List<Post> posts = service.getAllPost();
        System.out.println("All postings");
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find posting by id", notes = "Return an especific post registered" )
    public @ResponseBody
    ResponseEntity<Post> getPostById(@PathVariable String id) {
        logger.info("Consuming getPostById");
        Post post = service.getPostById(Long.parseLong(id));
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }


    @PostMapping("/")
    @ApiOperation(value = "Create a new post", notes = "Return a message CREATED o OK depend on method success" )
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
    @ApiOperation(value = "Update postings", notes = "Return Post objected updated before execute method" )
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
    @ApiOperation(value = "Delete Posting", notes = "Return a message depend on method success" )
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
