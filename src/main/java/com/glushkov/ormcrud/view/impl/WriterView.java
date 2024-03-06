package com.glushkov.ormcrud.view.impl;

import com.glushkov.ormcrud.controller.impl.PostController;
import com.glushkov.ormcrud.controller.impl.WriterController;
import com.glushkov.ormcrud.model.Post;
import com.glushkov.ormcrud.model.Status;
import com.glushkov.ormcrud.model.Writer;
import com.glushkov.ormcrud.exceprion.MyException;
import com.glushkov.ormcrud.view.Message;
import com.glushkov.ormcrud.view.View;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriterView implements View {

    private final WriterController WRITER_CONTROLLER = new WriterController();
    private final PostController POST_CONTROLLER = new PostController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            WRITER_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            Writer writer = WRITER_CONTROLLER.getByID(id);
            System.out.println("Текущий элемент:\n" + writer);
            addWriter(writer);
            WRITER_CONTROLLER.edit(writer);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getAll() {
        try {
            System.out.println(WRITER_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Writer writer = new Writer();
        addWriter(writer);
        writer.setStatus(Status.ACTIVE);
        writer.setCreated(Date.valueOf(LocalDate.now()));
        WRITER_CONTROLLER.add(writer);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(WRITER_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addWriter(Writer writer) {
        System.out.println(Message.ENTER_FIRSTNAME);
        writer.setFirstName(SC.next());

        System.out.println(Message.ENTER_LASTNAME);
        writer.setLastName(SC.next());

        Collection<Post> posts = POST_CONTROLLER.getAll();
        System.out.println(posts);
        System.out.println(Message.ENTER_POSTS);
        writer.setPosts(Stream.of(SC.next().split(","))
                .mapToLong(Long::parseLong)
                .mapToObj(POST_CONTROLLER::getByID)
                .collect(Collectors.toList()));
    }
}
