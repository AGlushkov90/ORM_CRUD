package com.glushkov.ormcrud.controller.impl;

import com.glushkov.ormcrud.model.Writer;
import com.glushkov.ormcrud.service.WriterService;

import java.util.Collection;

public class WriterController {
    private final WriterService writerService = new WriterService();

    public Writer getByID(long id) {
        return writerService.getByID(id);
    }

    public void add(Writer writer) {
        writerService.add(writer);
    }

    public Collection<Writer> getAll() {
        return writerService.getAll();
    }

    public void delete(long id) {
        writerService.delete(id);
    }

    public void edit(Writer writer) {
        writerService.edit(writer);
    }
}
