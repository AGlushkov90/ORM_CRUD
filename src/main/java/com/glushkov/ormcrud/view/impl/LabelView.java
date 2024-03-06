package com.glushkov.ormcrud.view.impl;

import com.glushkov.ormcrud.controller.impl.LabelController;
import com.glushkov.ormcrud.model.Label;
import com.glushkov.ormcrud.exceprion.MyException;
import com.glushkov.ormcrud.model.Status;
import com.glushkov.ormcrud.view.Message;
import com.glushkov.ormcrud.view.View;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class LabelView implements View {

    private final LabelController LABEL_CONTROLLER = new LabelController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);
        try {
            LABEL_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            Label labelEdit = LABEL_CONTROLLER.getByID(id);
            if (labelEdit == null) {
                System.out.println("Элемент не найден");
                return;
            }
            System.out.println("Текущий элемент:\n" + labelEdit);
            labelEdit.setId(id);
            addLabel(labelEdit);

            LABEL_CONTROLLER.edit(labelEdit);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addLabel(Label label) {
        System.out.println(Message.ENTER_NAME);
        label.setName(SC.next());
    }

    @Override
    public void getAll() {
        try {
            System.out.println(LABEL_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Label label = new Label();
        addLabel(label);
        label.setStatus(Status.ACTIVE);
        label.setCreated(Date.valueOf(LocalDate.now()));
        LABEL_CONTROLLER.add(label);
    }

    @Override
    public void getByID() {
        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(LABEL_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
