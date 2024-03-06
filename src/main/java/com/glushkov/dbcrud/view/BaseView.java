package com.glushkov.dbcrud.view;

import com.glushkov.dbcrud.view.impl.LabelView;
import com.glushkov.dbcrud.view.impl.PostView;
import com.glushkov.dbcrud.view.impl.WriterView;

import java.util.Scanner;

public class BaseView {
    private final static Scanner sc = new Scanner(System.in);

    public static void start() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println(Message.MENU);
            int answer = sc.nextInt();
            switch (answer) {
                case (1) -> showMenu(new WriterView());
                case (2) -> showMenu(new PostView());
                case (3) -> showMenu(new LabelView());
                case (4) -> isExit = true;
                default -> System.out.println("Ошибка при вводе числа");
            }
        }
    }

    private static void showMenu(View view) {
        System.out.println(Message.MENU_ITEM);
        switch (sc.nextInt()) {
            case (1) -> view.getByID();
            case (2) -> view.getAll();
            case (3) -> view.add();
            case (4) -> view.edit();
            case (5) -> view.delete();
            case (6) -> start();
            default -> System.out.println("Ошибка при вводе числа");
        }
    }
}
