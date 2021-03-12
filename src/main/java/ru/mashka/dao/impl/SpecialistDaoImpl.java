package ru.mashka.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.mashka.dao.SpecialistDao;
import ru.mashka.model.entity.Specialist;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpecialistDaoImpl implements SpecialistDao {

    private final SessionFactory sessionFactory;

    @Inject
    public SpecialistDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Specialist specialist) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(specialist);
    }

    @Override
    public Specialist getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Specialist.class, id);
    }

    @Override
    public void update(Specialist specialist) {
        Session session = sessionFactory.getCurrentSession();
        session.update(specialist);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Specialist where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
