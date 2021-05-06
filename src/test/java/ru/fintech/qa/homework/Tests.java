package ru.fintech.qa.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public void selectTestHibernatePo(){
        System.out.println(new DbClient().getAnimalByName("По"));
    }


    @Test
    public void exactly10lines(){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
       // Animal animal = session.createNativeQuery("select * from animal", Animal.class).getResultList().get(9);
//        System.out.println("The name is: " + animal.getName());
//        Assertions.assertEquals(10,animal.getId());
        List<Animal> id = (List<Animal>) session.createNativeQuery("select * from animal")
        .list();
        Assertions.assertEquals(10, id.size());
        sessionFactory.close();
    }

    @Test
    public void cannotAddAStringWithIndex1(){
        Animal animal = new Animal();
        animal.setName("Tanos");
        animal.setAge(25);
        animal.setType(1);
        animal.setId(1);
        animal.setPlace(1);
        animal.setSex(1);
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(animal);
        System.out.println(new DbClient().getAnimalByName("Бусинка"));
        sessionFactory.close();
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