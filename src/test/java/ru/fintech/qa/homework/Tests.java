package ru.fintech.qa.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.Animal;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.HibernateSessionCreator;

public class Tests {
    @BeforeAll
    public static void beforeAll(){
        BeforeUtils.createData();
    }

    @Test
    public void insertTestHibernate(){
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();

        Session session = sessionFactory.openSession();
        Animal animal = session.createNativeQuery("select * from animal where \"name\" = 'По'", Animal.class).getResultList().get(0);
        System.out.println(animal.getName());
    }
}