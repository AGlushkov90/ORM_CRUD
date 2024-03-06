package com.glushkov.dbcrud.repository.impl.jdbc;

import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.repository.PostRepository;
import com.glushkov.dbcrud.view.Message;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.*;

public class JDBCPostRepositoryImpl implements PostRepository {

    @Override
    public Post getByID(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM \"Post\" p left join \"Post_Label\" pl on p.id = pl.\"Post_id\" inner join \"Label\" l \n" +
                             "on pl.\"Label_id\" = l.id where p.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;
            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                if (post == null) {
                    post = resultSetToPost(resultSet);
                }
                long labelId = resultSet.getLong("Label_id");
                if (labelId != 0) {
                    labels.add(new Label(labelId, resultSet.getString("name")));
                }
            }
            if (post == null) {
                throw new RuntimeException(Message.NOT_FIND_ID.toString());
            }
            post.setLabels(labels);
            return post;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Collection<Post> getAll() {
        return getAllPostsInternal();
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"Post\" WHERE id=?")) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Post save(Post itemToSave) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"Post\" (title, " +
                     "content, created, status) VALUES (?, ?, ?, ?) RETURNING id");
             PreparedStatement preparedStatementLabels = connection.prepareStatement("INSERT INTO \"Post_Label\" (\"Label_id\", " +
                     "\"Post_id\") VALUES (?, ?)")) {
            preparedStatement.setString(1, itemToSave.getTitle());
            preparedStatement.setString(2, itemToSave.getContent());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(4, itemToSave.getStatus().getId());
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            int postId = 0;
            while (resultSet.next()) {
                postId = resultSet.getInt("id");
            }
            if (postId == 0) {
                throw new RuntimeException("Post not found");
            }
            for (Label label : itemToSave.getLabels()) {
                preparedStatementLabels.setLong(1, label.getId());
                preparedStatementLabels.setInt(2, postId);
                preparedStatementLabels.addBatch();
            }
            preparedStatementLabels.executeBatch();
            connection.commit();
            itemToSave.setId(postId);
            return itemToSave;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Post edit(Post postToUpdate) throws MyException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"Post\" SET title = ?," +
                     "content = ?, updated = ?, status = ? WHERE id = ?");
             PreparedStatement preparedStatementLabels = connection.prepareStatement("INSERT INTO \"Post_Label\" (\"Label_id\", " +
                     "\"Post_id\") VALUES (?, ?)");
             PreparedStatement preparedStatementDelete = connection.prepareStatement("DELETE FROM \"Post_Label\" WHERE \"Post_id\" = ?")
        ) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, postToUpdate.getTitle());
            preparedStatement.setString(2, postToUpdate.getContent());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(4, postToUpdate.getStatus().getId());
            preparedStatement.setLong(5, postToUpdate.getId());
            preparedStatement.execute();
            preparedStatementDelete.setLong(1, postToUpdate.getId());
            preparedStatementDelete.execute();
            for (Label label : postToUpdate.getLabels()) {
                preparedStatementLabels.setLong(1, label.getId());
                preparedStatementLabels.setLong(2, postToUpdate.getId());
                preparedStatementLabels.addBatch();
            }
            preparedStatementLabels.executeBatch();
            connection.commit();
            return postToUpdate;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }


    private Collection<Post> getAllPostsInternal() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM \"Post\" p left join \"Post_Label\" pl on p.id = pl.\"Post_id\" inner join \"Label\" l \n" +
                             "on pl.\"Label_id\" = l.id ORDER BY p.\"id\"")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;
            List<Label> labels = new ArrayList<>();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                if (post == null || post.getId() != resultSet.getInt("id")) {
                    if (post != null) {
                        post.setLabels(labels);
                        posts.add(post);
                    }
                    post = resultSetToPost(resultSet);
                    labels = new ArrayList<>();
                }
                long labelId = resultSet.getLong("Label_id");
                if (labelId != 0) {
                    labels.add(new Label(labelId, resultSet.getString("name")));
                }
            }
            if (post != null) {
                post.setLabels(labels);
                posts.add(post);
            }
            return posts;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
