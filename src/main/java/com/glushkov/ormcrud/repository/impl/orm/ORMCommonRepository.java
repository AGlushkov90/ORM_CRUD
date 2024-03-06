package com.glushkov.ormcrud.repository.impl.orm;

import com.glushkov.ormcrud.model.Label;
import com.glushkov.ormcrud.model.Post;
import com.glushkov.ormcrud.model.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMCommonRepository {

    private final static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Writer.class)
                .addAnnotatedClass(Post.class)
                .addAnnotatedClass(Label.class)
                .buildSessionFactory();
    }

    public static synchronized Session getSession() {
        return sessionFactory.openSession();
    }
}
