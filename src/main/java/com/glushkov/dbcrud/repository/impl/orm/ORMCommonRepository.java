package com.glushkov.dbcrud.repository.impl.orm;

import com.glushkov.dbcrud.model.Label;
import com.glushkov.dbcrud.model.Post;
import com.glushkov.dbcrud.model.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ORMCommonRepository {

    private static SessionFactory sessionFactory;
    static {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Writer.class)
                .addAnnotatedClass(Post.class)
                .addAnnotatedClass(Label.class)
                .buildSessionFactory();
    }

    public static synchronized Session getSession(){
        return sessionFactory.openSession();
    }

}
