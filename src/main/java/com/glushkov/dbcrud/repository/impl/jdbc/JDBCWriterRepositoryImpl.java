package com.glushkov.dbcrud.repository.impl.jdbc;

import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.model.*;
import com.glushkov.dbcrud.repository.WriterRepository;
import com.glushkov.dbcrud.view.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.*;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer getByID(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM \"Writer\" p left join \"Writer_Post\" pl on p.id = pl.\"Writer_id\" inner join \"Post\" l \n" +
                             "on pl.\"Post_id\" = l.id inner join \"Post_Label\" wl on l.\"id\" = wl.\"Post_id\" " +
                             "inner join \"Label\" ll on ll.\"id\" = wl.\"Label_id\" WHERE p.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Writer writer = null;
            Post post = null;
            List<Post> posts = new ArrayList<>();
            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                if (writer == null) {
                    writer = resultSetToWriter(resultSet);
                }
                long postId = resultSet.getLong("Post_id");
                if (postId != 0) {
                    if (post == null || post.getId() != resultSet.getInt("id")) {
                        if (post != null) {
                            post.setLabels(labels);
                            posts.add(post);
                        }
                        post = resultSetToPost(resultSet, postId);
                        labels = new ArrayList<>();
                    }
                    long labelId = resultSet.getLong("Label_id");
                    if (labelId != 0) {
                        labels.add(new Label(labelId, resultSet.getString("name")));
                    }
                }
            }
            if (writer == null) {
                throw new RuntimeException(Message.NOT_FIND_ID.toString());
            }
            if (post != null) {
                post.setLabels(labels);
                posts.add(post);
            }
            writer.setPosts(posts);
            return writer;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Collection<Writer> getAll() {
        return getAllWritersInternal();
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"Writer\" WHERE id=?")) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Writer save(Writer itemToSave) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"Writer\" (\"firstName\", " +
                     "\"lastName\") VALUES (?, ?) RETURNING id");
             PreparedStatement preparedStatementPosts = connection.prepareStatement("INSERT INTO \"Writer_Post\" (\"Writer_id\", " +
                     "\"Post_id\") VALUES (?, ?)")) {
            preparedStatement.setString(1, itemToSave.getFirstName());
            preparedStatement.setString(2, itemToSave.getLastName());
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            int writerId = 0;
            while (resultSet.next()) {
                writerId = resultSet.getInt("id");
            }
            if (writerId == 0) {
                throw new RuntimeException("Writer not found");
            }
            for (Post post : itemToSave.getPosts()) {
                preparedStatementPosts.setLong(1, writerId);
                preparedStatementPosts.setLong(2, post.getId());
                preparedStatementPosts.addBatch();
            }
            preparedStatementPosts.executeBatch();
            connection.commit();
            itemToSave.setId(writerId);
            return itemToSave;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Writer edit(Writer writerToUpdate) throws MyException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"Writer\" SET \"firstName\" = ?," +
                     "\"lastName\" = ? WHERE id = ?");
             PreparedStatement preparedStatementPosts = connection.prepareStatement("INSERT INTO \"Writer_Post\" (\"Writer_id\", " +
                     "\"Post_id\") VALUES (?, ?)");
             PreparedStatement preparedStatementDelete = connection.prepareStatement("DELETE FROM \"Writer_Post\" WHERE \"Writer_id\"= ?")
        ) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, writerToUpdate.getFirstName());
            preparedStatement.setString(2, writerToUpdate.getLastName());
            preparedStatement.setLong(3, writerToUpdate.getId());
            preparedStatement.execute();
            preparedStatementDelete.setLong(1, writerToUpdate.getId());
            preparedStatementDelete.execute();

            for (Post post : writerToUpdate.getPosts()) {
                preparedStatementPosts.setLong(1, writerToUpdate.getId());
                preparedStatementPosts.setLong(2, post.getId());
                preparedStatementPosts.addBatch();
            }
            preparedStatementPosts.executeBatch();
            connection.commit();
            return writerToUpdate;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private Collection<Writer> getAllWritersInternal() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM \"Writer\" p left join \"Writer_Post\" pl on p.id = pl.\"Writer_id\" inner join \"Post\" l \n" +
                             "on pl.\"Post_id\" = l.id inner join \"Post_Label\" wl on l.\"id\" = wl.\"Post_id\" " +
                             "inner join \"Label\" ll on ll.\"id\" = wl.\"Label_id\" ORDER BY p.id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Writer writer = null;
            List<Post> posts = new ArrayList<>();
            List<Writer> writers = new ArrayList<>();
            Post post = null;
            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                boolean writerChange = false;
                if (writer == null || writer.getId() != resultSet.getInt("id")) {
                    if (writer != null) {
                        post.setLabels(labels);
                        posts.add(post);
                        post = null;
                        writer.setPosts(posts);
                        writers.add(writer);
                    }
                    writer = resultSetToWriter(resultSet);
                    posts = new ArrayList<>();
                    labels = new ArrayList<>();
                    writerChange = true;
                }
                long postId = resultSet.getLong("Post_id");
                if (postId != 0) {
                    if (!writerChange && (post == null || post.getId() != postId)) {
                        if (post != null) {
                            post.setLabels(labels);
                            posts.add(post);
                        }
                        post = resultSetToPost(resultSet, postId);
                        labels = new ArrayList<>();
                    }
                    long labelId = resultSet.getLong("Label_id");
                    if (labelId != 0) {
                        labels.add(new Label(labelId, resultSet.getString("name")));
                    }
                }
            }
            if (post != null) {
                post.setLabels(labels);
                posts.add(post);
            }
            if (writer != null) {
                writer.setPosts(posts);
                writers.add(writer);
            }
            return writers;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

