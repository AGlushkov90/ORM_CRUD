package com.glushkov.dbcrud.repository.impl.orm;

import com.glushkov.dbcrud.exceprion.MyException;
import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.model.Writer;
import com.glushkov.dbcrud.repository.WriterRepository;
import com.glushkov.dbcrud.view.Message;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.resultSetToPost;
import static com.glushkov.dbcrud.util.Mapper.resultSetToWriter;

public class ORMWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer getByID(Long id) {
        try(Session session = ORMCommonRepository.getSession()){
            return session.get(Writer.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Collection<Writer> getAll() {
        try(Session session = ORMCommonRepository.getSession()){
            List<Writer> writers = session.createQuery("FROM Writer").list();
            return writers;
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
            Writer writer = session.load(Writer.class, id);
            session.delete(writer);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Writer save(Writer itemToSave) {
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
    public Writer edit(Writer itemToUpdate) {
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

