package ru.mashka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.mashka.injection.InjectionExtension;
import ru.mashka.model.entity.Specialist;

import javax.inject.Inject;

@ExtendWith(InjectionExtension.class)
public class SpecialistDaoTest {

    @Inject
    SpecialistDao specialistDao;

    @Inject
    private SessionFactory sessionFactory;

    @Test
    void testSpecDao() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Specialist specialist = new Specialist("first", "last", "patronymic", 1);
            specialistDao.add(specialist);
            Specialist specialist1 = new Specialist("Alina", "Ivanova", " ", 2);
            specialistDao.add(specialist1);
            Specialist byId = specialistDao.getById(11);
            byId.setFirstName("Anna");
            byId.setLastName("Saimon");
            byId.setPatronymic("test");
            specialistDao.update(byId);
            specialistDao.delete(12);
            tr.rollback();
        }
    }
}
