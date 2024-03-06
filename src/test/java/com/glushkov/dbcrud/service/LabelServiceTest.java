package com.glushkov.dbcrud.service;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.repository.LabelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class LabelServiceTest {

    @Mock
    private LabelRepository labelRepository;

    @InjectMocks
    private LabelService labelService;

    @Test
    void whenGivenId_shouldReturnLabel() {
        Label label = new Label(1L, "sport");
        when(labelRepository.getByID(label.getId())).thenReturn(label);
        Label labelResult = labelService.getByID(label.getId());
        assertEquals(label, labelResult);
        verify(labelRepository).getByID(label.getId());
    }

    @Test
    void whenGivenId_shouldReturnNull() {
        Label label = new Label(3L, "sport");
        when(labelRepository.getByID(label.getId())).thenReturn(null);
        Label labelResult = labelService.getByID(label.getId());
        assertNull(labelResult);
        verify(labelRepository).getByID(label.getId());
    }

    @Test
    void whenSaveLabel_shouldReturnLabel() {
        Label label = new Label(1L, "sport");
        when(labelRepository.save(ArgumentMatchers.any(Label.class))).thenReturn(label);
        Label created = labelService.add(label);
        assertEquals(label, created);
        verify(labelRepository).save(label);
    }

    @Test
    void shouldReturnAllLabel() {
        List<Label> labels = Arrays.asList(new Label(1L, "sport"), new Label(2L, "music"));
        when(labelRepository.getAll()).thenReturn(labels);
        List<Label> labelsResult = (List<Label>) labelService.getAll();
        assertEquals(labels, labelsResult);
        verify(labelRepository).getAll();
    }

    @Test
    void whenGivenId_shouldDeleteLabel_ifFound() {
        Label label = new Label(1L, "sport");
        when(labelRepository.delete(ArgumentMatchers.any(Long.class))).thenReturn(true);
        boolean deleted = labelService.delete(1);
        assertTrue(deleted);
        verify(labelRepository).delete(label.getId());
    }

    @Test
    void edit() {
        Label label = new Label(1L, "sport");
        Label newLabel = new Label(1L, "new sport");
        when(labelRepository.edit(ArgumentMatchers.any(Label.class))).thenReturn(newLabel);
        Label edited = labelService.edit(newLabel);
        assertEquals(label.getId(), edited.getId());
        assertEquals(newLabel, edited);
        verify(labelRepository).edit(newLabel);

    }
}