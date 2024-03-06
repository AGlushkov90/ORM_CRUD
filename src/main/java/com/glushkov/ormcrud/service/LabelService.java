package com.glushkov.ormcrud.service;

import com.glushkov.ormcrud.model.Label;
import com.glushkov.ormcrud.repository.LabelRepository;
import com.glushkov.ormcrud.repository.impl.jdbc.JDBCLabelRepositoryImpl;
import com.glushkov.ormcrud.repository.impl.orm.ORMLabelRepositoryImpl;

import java.util.Collection;

public class LabelService {
    public LabelService() {
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    private LabelRepository labelRepository = new ORMLabelRepositoryImpl();

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
