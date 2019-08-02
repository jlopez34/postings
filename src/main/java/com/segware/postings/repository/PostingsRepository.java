package com.segware.postings.repository;

import com.segware.postings.model.Post;

import java.util.List;

public interface PostingsRepository {

    public List<Post> getAll();

    public Post getById(Long postId);

    public String create(Post post);

    public Post update(Long id, Post post);

    public String delete(Long id);
}
