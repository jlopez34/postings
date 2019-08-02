package com.segware.postings.service;

import com.segware.postings.model.Post;

import java.util.List;

public interface PostingsService {

    public List<Post> getAllPost();

    public Post getPostById(Long postId);

    public String createPost(Post post);

    public Post updatePost(Long id, Post post);

    public String deletePost(Long id);

}
