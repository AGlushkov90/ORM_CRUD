package com.glushkov.dbcrud.view.impl;

import com.glushkov.dbcrud.controller.impl.LabelController;
import com.glushkov.dbcrud.controller.impl.PostController;
import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.model.Status;
import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.view.Message;
import com.glushkov.dbcrud.view.View;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostView implements View {

    private final PostController POST_CONTROLLER = new PostController();
    private final LabelController LABEL_CONTROLLER = new LabelController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            POST_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            Post currentPost = POST_CONTROLLER.getByID(id);
            System.out.println("Текущий элемент:\n" + currentPost);

            Post post = new Post(id, currentPost.getCreated(), currentPost.getStatus());
            post.setUpdated(Date.valueOf(LocalDate.now()));
            addPost(post);

            POST_CONTROLLER.edit(post);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addPost(Post post) {
        System.out.println(Message.ENTER_TITLE);
        post.setTitle(SC.next());

        System.out.println(Message.ENTER_CONTENT);
        post.setContent(SC.next());

        Collection<Label> labels = LABEL_CONTROLLER.getAll();
        System.out.println(labels);
        System.out.println(Message.ENTER_LABEL);
        post.setLabels(Stream.of(SC.next().split(","))
                .mapToLong(Long::parseLong)
                .mapToObj(LABEL_CONTROLLER::getByID)
                .collect(Collectors.toList()));
    }

    @Override
    public void getAll() {
        try {
            System.out.println(POST_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Post post = new Post();
        addPost(post);
        post.setStatus(Status.ACTIVE);
        POST_CONTROLLER.add(post);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(POST_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
