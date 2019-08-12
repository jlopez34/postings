package com.segware.postings;

import com.segware.postings.model.Post;
import com.segware.postings.repository.PostingsRepository;
import com.segware.postings.service.PostingsService;
import com.segware.postings.service.impl.PostingsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostingsApplicationTests {

    @Mock
    private PostingsRepository postingsRepository;

    @InjectMocks
    private PostingsService postingsService = new PostingsServiceImpl(postingsRepository);

    @BeforeEach
    void setMockOutput() {
        final OngoingStubbing<List<Post>> listOngoingStubbing = when(postingsRepository.getAll()).thenReturn(new ArrayList<Post>());
    }

    @DisplayName("Test Mock get all posting")
    @Test
    void testGetPostings() {
        assertEquals(new ArrayList<Post>(), postingsService.getAllPost());
    }

    @DisplayName("Test Mock updated posting")
    @Test
    void testUpdatePostings() {
        Post post = new Post("Test1", "Comment1", 0, 0);
        post.setId(1);
        assertEquals(0, postingsService.updatePost(post));
    }

    @DisplayName("Test Mock delete post")
    @Test
    void testDeletePostings() {
        Post post = new Post("Test1", "Comment1", 0, 0);
        post.setId(1);
        assertEquals(0, postingsService.deletePost(Long.parseLong(String.valueOf(post.getId()))));
    }
}
