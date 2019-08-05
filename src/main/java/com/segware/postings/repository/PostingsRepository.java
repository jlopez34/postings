package com.segware.postings.repository;

import com.segware.postings.model.Post;

import java.util.List;

public interface PostingsRepository {

    public List<Post> getAll();

    public Post getById(Long postId);

    public int create(Post post);

    public int update(Post post);

    public int delete(Long id);
}
