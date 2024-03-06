package com.glushkov.ormcrud.service;

import com.glushkov.ormcrud.model.Writer;
import com.glushkov.ormcrud.repository.WriterRepository;
import com.glushkov.ormcrud.repository.impl.jdbc.JDBCWriterRepositoryImpl;
import com.glushkov.ormcrud.repository.impl.orm.ORMWriterRepositoryImpl;

import java.util.Collection;

public class WriterService {

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public WriterService() {
    }

    private WriterRepository writerRepository = new ORMWriterRepositoryImpl();

    public Writer getByID(long id) {
        return writerRepository.getByID(id);
    }

    public Writer add(Writer writer) {
        return writerRepository.save(writer);
    }

    public Collection<Writer> getAll() {
        return writerRepository.getAll();
    }

    public boolean delete(long id) {
        return writerRepository.delete(id);
    }

    public Writer edit(Writer writer) {
        return writerRepository.edit(writer);
    }
}
