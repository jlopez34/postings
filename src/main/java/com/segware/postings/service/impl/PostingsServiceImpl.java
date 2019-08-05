package com.segware.postings.service.impl;

import com.segware.postings.model.Post;
import com.segware.postings.repository.PostingsRepository;
import com.segware.postings.service.PostingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingsServiceImpl implements PostingsService {

    @Autowired
    private PostingsRepository respository;

    public PostingsServiceImpl(PostingsRepository respository) {
        this.respository = respository;
    }

    @Override
    public List<Post> getAllPost() {
        return respository.getAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return respository.getById(postId);
    }

    @Override
    public int createPost(Post post) {
        return respository.create(post);
    }

    @Override
    public int updatePost(Post post) {
        return respository.update(post);
    }

    @Override
    public int deletePost(Long id) {
        return respository.delete(id);
    }
}
