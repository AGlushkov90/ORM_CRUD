package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.repository.LabelRepository;
import com.glushkov.dbcrud.repository.impl.jdbc.JDBCLabelRepositoryImpl;

import java.util.Collection;

public class LabelService {
    public LabelService() {
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    private LabelRepository labelRepository = new JDBCLabelRepositoryImpl();

    public Label getByID(long id) {
        return labelRepository.getByID(id);
    }

    public Label add(Label label) {
        return labelRepository.save(label);
    }

    public Collection<Label> getAll() {
        return labelRepository.getAll();
    }

    public boolean delete(long id) {
        return labelRepository.delete(id);
    }

    public Label edit(Label label) {
        return labelRepository.edit(label);
    }
}
