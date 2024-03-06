package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.model.Status;
import com.glushkov.dbcrud.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void whenGivenId_shouldReturnPost() {
        Post post = new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        when(postRepository.getByID(post.getId())).thenReturn(post);
        Post postResult = postService.getByID(post.getId());
        assertEquals(post, postResult);
        verify(postRepository).getByID(post.getId());
    }

    @Test
    void whenGivenId_shouldReturnNull() {
        Post post = new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        when(postRepository.getByID(post.getId())).thenReturn(null);
        Post postResult = postService.getByID(post.getId());
        assertNull(postResult);
        verify(postRepository).getByID(post.getId());
    }

    @Test
    void whenSavePost_shouldReturnPost() {
        Post post = new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        when(postRepository.save(ArgumentMatchers.any(Post.class))).thenReturn(post);
        Post created = postService.add(post);
        assertEquals(post, created);
        verify(postRepository).save(post);
    }

    @Test
    void shouldReturnAllPost() {
        List<Post> posts = Arrays.asList(
                new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE),
                new Post(2L, "music concert", "rap", Date.valueOf(LocalDate.now()), null, Status.ACTIVE));
        when(postRepository.getAll()).thenReturn(posts);
        List<Post> postsResult = (List<Post>) postService.getAll();
        assertEquals(posts, postsResult);
        verify(postRepository).getAll();
    }

    @Test
    void whenGivenId_shouldDeletePost_ifFound() {
        Post post = new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        when(postRepository.delete(ArgumentMatchers.any(Long.class))).thenReturn(true);
        boolean deleted = postService.delete(1);
        assertTrue(deleted);
        verify(postRepository).delete(post.getId());
    }

    @Test
    void updatePost_whenPutPost() {
        Post post = new Post(1L, "game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        Post newPost = new Post(1L, "new game", "football game", Date.valueOf(LocalDate.now()), null, Status.ACTIVE);
        when(postRepository.edit(ArgumentMatchers.any(Post.class))).thenReturn(newPost);
        Post edited = postService.edit(newPost);
        assertEquals(post.getId(), edited.getId());
        assertEquals(newPost, edited);
        verify(postRepository).edit(newPost);

    }
}