package com.glushkov.dbcrud.util;

import static com.glushkov.dbcrud.model.Status.getById;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {
    public static Label resultSetToLabel(ResultSet resultSet) throws SQLException {
        return new Label(resultSet.getLong("id"), resultSet.getString("name"));
    }

    public static Post resultSetToPost(ResultSet resultSet) throws SQLException {
        return new Post(resultSet.getLong("id"), resultSet.getString("title"),
                resultSet.getString("content"), resultSet.getDate("created"),
                resultSet.getDate("updated"), getById(resultSet.getInt("status")));
    }

    public static Post resultSetToPost(ResultSet resultSet, Long postId) throws SQLException {
        return new Post(postId, resultSet.getString("title"),
                resultSet.getString("content"), resultSet.getDate("created"),
                resultSet.getDate("updated"), getById(resultSet.getInt("status")));
    }

    public static Writer resultSetToWriter(ResultSet resultSet) throws SQLException {
        return new Writer(resultSet.getLong("id"), resultSet.getString("firstName"),
                resultSet.getString("lastName"));
    }
}


