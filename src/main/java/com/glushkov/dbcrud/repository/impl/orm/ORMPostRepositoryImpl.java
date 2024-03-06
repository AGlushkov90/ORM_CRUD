package com.glushkov.dbcrud.repository.impl.orm;

import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.repository.PostRepository;
import com.glushkov.dbcrud.view.Message;
import org.hibernate.HibernateException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.resultSetToPost;

public class ORMPostRepositoryImpl implements PostRepository {

    @Override
    public Post getByID(Long id) {
        try(Session session = ORMCommonRepository.getSession()){
            return session.get(Post.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Collection<Post> getAll() {
        try(Session session = ORMCommonRepository.getSession()){
            List<Post> posts = session.createQuery("FROM Post").list();
            return posts;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Transaction tx = null;
        try(Session session = ORMCommonRepository.getSession()){
            tx = session.beginTransaction();
            Post post = session.load(Post.class, id);
            session.delete(post);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Post save(Post itemToSave) {
        Transaction tx = null;
        try(Session session = ORMCommonRepository.getSession()){
            tx = session.beginTransaction();
            session.merge(itemToSave);
            tx.commit();
            return itemToSave;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post edit(Post itemToUpdate) {
        Transaction tx = null;
        try(Session session = ORMCommonRepository.getSession()){
            tx = session.beginTransaction();
            session.update(itemToUpdate);
            tx.commit();
            return itemToUpdate;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return null;
    }
}
