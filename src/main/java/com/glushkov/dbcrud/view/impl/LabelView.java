package com.glushkov.dbcrud.view.impl;

import com.glushkov.dbcrud.controller.impl.LabelController;
import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.view.Message;
import com.glushkov.dbcrud.view.View;

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
            Label label = new Label();
            label.setId(id);
            addLabel(label);

            LABEL_CONTROLLER.edit(label);
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
