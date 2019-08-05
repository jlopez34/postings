package com.segware.postings.service;

import com.segware.postings.model.Post;

import java.util.List;

public interface PostingsService {

    public List<Post> getAllPost();

    public Post getPostById(Long postId);

    public int createPost(Post post);

    public int updatePost(Post post);

    public int deletePost(Long id);

}
