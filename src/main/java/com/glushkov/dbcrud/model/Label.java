package com.glushkov.dbcrud.model;

public class Label extends BaseItem {
    private String name;

    public Label(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Label() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" + super.toString() +
                "name='" + name + '\'' +
                "}\n";
    }
}
