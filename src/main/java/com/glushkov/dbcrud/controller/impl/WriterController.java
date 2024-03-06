package com.glushkov.dbcrud.controller.impl;

import com.glushkov.dbcrud.model.Writer;
import com.glushkov.dbcrud.service.WriterService;

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
