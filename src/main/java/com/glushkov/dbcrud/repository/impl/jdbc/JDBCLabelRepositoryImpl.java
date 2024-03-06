package com.glushkov.dbcrud.repository.impl.jdbc;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.repository.LabelRepository;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.resultSetToLabel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCLabelRepositoryImpl implements LabelRepository {

    @Override
    public Label getByID(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM \"Label\" WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSetToLabel(resultSet);
            }
            return null;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Collection<Label> getAll() {
        return getAllLabelsInternal();
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"Label\" WHERE id=?")) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Label save(Label itemToSave) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"Label\" (name) VALUES (?)")) {
            preparedStatement.setString(1, itemToSave.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                itemToSave.setId(resultSet.getLong("id"));
                return itemToSave;
            }

            return null;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Label edit(Label labelToUpdate) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"Label\" SET name = ? WHERE id = ?")) {
            preparedStatement.setString(1, labelToUpdate.getName());
            preparedStatement.setLong(2, labelToUpdate.getId());
            preparedStatement.execute();
            return labelToUpdate;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private Collection<Label> getAllLabelsInternal() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"Label\" ORDER BY id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                labels.add(resultSetToLabel(resultSet));
            }
            return labels;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
