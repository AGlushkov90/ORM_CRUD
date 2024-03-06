package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Writer;
import com.glushkov.dbcrud.repository.WriterRepository;
import com.glushkov.dbcrud.repository.impl.jdbc.JDBCWriterRepositoryImpl;

import java.util.Collection;

public class WriterService {

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public WriterService() {
    }

    private WriterRepository writerRepository = new JDBCWriterRepositoryImpl();

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
