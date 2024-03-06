package com.glushkov.ormcrud.repository.impl.orm;

import com.glushkov.ormcrud.model.Label;
import com.glushkov.ormcrud.repository.LabelRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

public class ORMLabelRepositoryImpl implements LabelRepository {


    @Override
    public Label getByID(Long id) {
        try (Session session = ORMCommonRepository.getSession()) {
            return session.get(Label.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Collection<Label> getAll() {
        try (Session session = ORMCommonRepository.getSession()) {
            return session.createQuery("FROM Label").list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Transaction tx = null;
        try (Session session = ORMCommonRepository.getSession()) {
            tx = session.beginTransaction();
            Label label = session.load(Label.class, id);
            session.delete(label);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Label save(Label itemToSave) {
        Transaction tx = null;
        try (Session session = ORMCommonRepository.getSession()) {
            tx = session.beginTransaction();
            session.persist(itemToSave);
            tx.commit();
            return itemToSave;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Label edit(Label labelToUpdate) {
        Transaction tx = null;
        try (Session session = ORMCommonRepository.getSession()) {
            tx = session.beginTransaction();

            session.update(labelToUpdate);
            tx.commit();
            return labelToUpdate;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        }
        return null;
    }
}
