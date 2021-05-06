package ru.fintech.qa.homework.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DbClient {

    public Animal getAnimalByName(String name){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createNativeQuery("select * from animal where \"name\" = '" + name + "'", Animal.class).getResultList().get(0);
    }
    public Animal getAnimalById(int id){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createNativeQuery("select * from animal where id = '" + id + "'", Animal.class).getResultList().get(0);
    }
    public Workman getWorkmanById(int id){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createNativeQuery("select * from workman where id = '" + id + "'", Workman.class).getResultList().get(0);
    }

}
