package ru.fintech.qa.homework;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @BeforeAll
    public static void beforeAll(){
        BeforeUtils.createData();
    }

    @Test
    public void justChecking(){
        System.out.println(new DbClient().getAnimalByName("По"));
    }


    @Test
    public void caseOne(){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Animal> id = (List<Animal>) session.createNativeQuery("select * from animal")
        .list();
        Assertions.assertEquals(10, id.size());
        sessionFactory.close();
    }

    @Test
    public void cannotAddAStringWithIndex1(){
        Transaction transaction = null;
        try (Session session = HibernateSessionCreator.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the animal object
            Animal animal = new Animal();
            animal.setName("Tanos");
            animal.setAge(25);
            animal.setType(1);
            animal.setId(1);
            animal.setPlace(1);
            animal.setSex(1);
            SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
            session.save(animal);
            // commit transaction
            List<Animal> id = (List<Animal>) session.createNativeQuery("select * from animal where id = 1")
                    .list();
            Assertions.assertEquals(1,1);
            transaction.commit();

            sessionFactory.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        System.out.println(new DbClient().getAnimalById(1));
    }

    @Test
    public void cannotAddAStringWithIndex15(){
        Animal animal = new Animal();
        animal.setName("Tanos");
        animal.setAge(25);
        animal.setType(1);
        animal.setId(15);
        animal.setPlace(1);
        animal.setSex(1);
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(animal);
        System.out.println(new DbClient().getAnimalByName("Tanos"));
        session.getTransaction().commit();
        sessionFactory.close();
    }

}