package com.glushkov.ormcrud.controller.impl;

import com.glushkov.ormcrud.model.Post;
import com.glushkov.ormcrud.service.PostService;

import java.util.Collection;

public class PostController {
    private final PostService postService = new PostService();

    public Post getByID(long id) {
        return postService.getByID(id);
    }

    public void add(Post post) {
        postService.add(post);
    }

    public Collection<Post> getAll() {
        return postService.getAll();
    }

    public void delete(long id) {
        postService.delete(id);
    }

    public void edit(Post post) {
        postService.edit(post);
    }
}
