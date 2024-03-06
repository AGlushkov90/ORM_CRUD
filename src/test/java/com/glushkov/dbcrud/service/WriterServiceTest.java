package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Writer;
import com.glushkov.dbcrud.repository.WriterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WriterServiceTest {

    @Mock
    private WriterRepository writerRepository;

    @InjectMocks
    private WriterService writerService;

    @Test
    void whenGivenId_shouldReturnWriter() {
        Writer writer = new Writer(1L, "Gary", "Wolf");
        when(writerRepository.getByID(writer.getId())).thenReturn(writer);
        Writer writerResult = writerService.getByID(writer.getId());
        assertEquals(writer, writerResult);
        verify(writerRepository).getByID(writer.getId());
    }

    @Test
    void whenGivenId_shouldReturnNull() {
        Writer writer = new Writer(1L, "Gary", "Wolf");
        when(writerRepository.getByID(writer.getId())).thenReturn(null);
        Writer writerResult = writerService.getByID(writer.getId());
        assertNull(writerResult);
        verify(writerRepository).getByID(writer.getId());
    }

    @Test
    void whenSaveWriter_shouldReturnWriter() {
        Writer writer = new Writer(1L, "Gary", "Wolf");
        when(writerRepository.save(ArgumentMatchers.any(Writer.class))).thenReturn(writer);
        Writer created = writerService.add(writer);
        assertEquals(writer, created);
        verify(writerRepository).save(writer);
    }

    @Test
    void shouldReturnAllWriter() {
        List<Writer> writers = Arrays.asList(
                new Writer(1L, "Gary", "Wolf"),
                new Writer(1L, "Isaac", "Asimov "));
        when(writerRepository.getAll()).thenReturn(writers);
        List<Writer> writersResult = (List<Writer>) writerService.getAll();
        assertEquals(writers, writersResult);
        verify(writerRepository).getAll();
    }

    @Test
    void whenGivenId_shouldDeleteWriter_ifFound() {
        Writer writer = new Writer(1L, "Gary", "Wolf");
        when(writerRepository.delete(ArgumentMatchers.any(Long.class))).thenReturn(true);
        boolean deleted = writerService.delete(1);
        assertTrue(deleted);
        verify(writerRepository).delete(writer.getId());
    }

    @Test
    void updateWriter_whenPutWriter() {
        Writer writer = new Writer(1L, "Gary", "Wolf");
        Writer newWriter = new Writer(1L, "Garry", "Wolf");
        when(writerRepository.edit(ArgumentMatchers.any(Writer.class))).thenReturn(newWriter);
        Writer edited = writerService.edit(newWriter);
        assertEquals(writer.getId(), edited.getId());
        assertEquals(newWriter, edited);
        verify(writerRepository).edit(newWriter);

    }
}