package com.glushkov.ormcrud.service;

import com.glushkov.ormcrud.model.Post;
import com.glushkov.ormcrud.repository.PostRepository;
import com.glushkov.ormcrud.repository.impl.jdbc.JDBCPostRepositoryImpl;
import com.glushkov.ormcrud.repository.impl.orm.ORMPostRepositoryImpl;

import java.util.Collection;

public class PostService {

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostService() {
    }

    private PostRepository postRepository = new ORMPostRepositoryImpl();

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
