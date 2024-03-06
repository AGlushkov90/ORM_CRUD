package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.repository.PostRepository;
import com.glushkov.dbcrud.repository.impl.jdbc.JDBCPostRepositoryImpl;

import java.util.Collection;

public class PostService {

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostService() {
    }

    private PostRepository postRepository = new JDBCPostRepositoryImpl();

    public Post getByID(long id) {
        return postRepository.getByID(id);
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Collection<Post> getAll() {
        return postRepository.getAll();
    }

    public boolean delete(long id) {
        return postRepository.delete(id);
    }

    public Post edit(Post post) {
        return postRepository.edit(post);
    }
}
