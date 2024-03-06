package com.glushkov.ormcrud.repository.impl.orm;

import com.glushkov.ormcrud.model.Post;
import com.glushkov.ormcrud.repository.PostRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;


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
