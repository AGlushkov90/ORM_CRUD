package com.glushkov.ormcrud.controller.impl;

import com.glushkov.ormcrud.model.Label;
import com.glushkov.ormcrud.service.LabelService;

import java.util.Collection;

public class LabelController {
    private final LabelService labelService = new LabelService();

    public Label getByID(long id) {
        return labelService.getByID(id);
    }

    public void add(Label label) {
        labelService.add(label);
    }

    public Collection<Label> getAll() {
        return labelService.getAll();
    }

    public void delete(long id) {
        labelService.delete(id);
    }

    public void edit(Label label) {
        labelService.edit(label);
    }
}
