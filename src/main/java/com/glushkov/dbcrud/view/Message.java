package com.glushkov.dbcrud.view;

public enum Message {
    MENU("""
            Выберите сущность (введите число):
            1 Writer
            2 Post
            3 Label
            4 Exit"""),

    MENU_ITEM("""
            Выберите действие (введите число):
            1 Get
            2 Get all
            3 Add
            4 Edit
            5 Delete
            6 Back"""),

    ENTER_ID("Введите id:"),
    ENTER_FIRSTNAME("Введите имя:"),
    ENTER_LASTNAME("Введите фамилию:"),
    ENTER_TITLE("Введите заголовок:"),
    ENTER_CONTENT("Введите контент:"),
    ENTER_WRITER("Введите id автора:"),
    ENTER_NAME("Введите имя:"),
    ENTER_LABEL("Введите id меток для поста через запятую:"),
    ENTER_POSTS("Введите id постов для поста через запятую:"),
    ENTER_POST("Введите id поста:"),
    COLLECTION_EMPTY("Коллекция пустая"),
    NOT_FIND_ID("Id не найден ");

    private final String text;

    Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
