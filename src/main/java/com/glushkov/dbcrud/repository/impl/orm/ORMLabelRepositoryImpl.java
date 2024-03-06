package com.glushkov.dbcrud.repository.impl.orm;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.repository.LabelRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.glushkov.dbcrud.repository.impl.jdbc.JDBCCommonRepository.getConnection;
import static com.glushkov.dbcrud.util.Mapper.resultSetToLabel;

public class ORMLabelRepositoryImpl implements LabelRepository {


    @Override
    public Label getByID(Long id) {
        try(Session session = ORMCommonRepository.getSession()){
            return session.get(Label.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Collection<Label> getAll() {

        try(Session session = ORMCommonRepository.getSession()){
            List<Label> labels = session.createQuery("FROM Label").list();
            return labels;
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
            Label label = session.load(Label.class, id);
            session.delete(label);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Label save(Label itemToSave) {
        Transaction tx = null;
        try(Session session = ORMCommonRepository.getSession()){
            tx = session.beginTransaction();
            session.persist(itemToSave);
            tx.commit();
            return itemToSave;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Label edit(Label labelToUpdate) {
        Transaction tx = null;
        try(Session session = ORMCommonRepository.getSession()){
            tx = session.beginTransaction();

            session.update(labelToUpdate);
            tx.commit();
            return labelToUpdate;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        return null;
    }
}
