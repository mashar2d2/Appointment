package ru.mashka.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.mashka.dao.SpecWorkTimeDao;
import ru.mashka.model.entity.SpecWorkTime;
import ru.mashka.model.key.SpecWorkTimeKey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.List;

@Singleton
public class SpecWorkTimeDaoImpl implements SpecWorkTimeDao {

    private final SessionFactory sessionFactory;

    @Inject
    public SpecWorkTimeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<SpecWorkTime> getBetween(int id, Instant from, Instant to) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM SpecWorkTime where specId = :id AND " +
                        ":from between workStart and workEnd " +
                        "OR :to between workStart and workEnd " +
                        "OR (workStart between :from and :to and " +
                        " workEnd between :from and :to) ",
                SpecWorkTime.class)
                .setParameter("id", id)
                .setParameter("from", from, TemporalType.TIMESTAMP)
                .setParameter("to", to, TemporalType.TIMESTAMP)
                .list();
    }

    @Override
    public void add(SpecWorkTime specWorkTime) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(specWorkTime);
    }

    @Override
    public void delete(SpecWorkTimeKey specWorkTimeKey) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM SpecWorkTime where specId = :specIdKey and " +
                " workStart = :workStartKey ");
        query.setParameter("specIdKey", specWorkTimeKey.getSpecId());
        query.setParameter("workStartKey", specWorkTimeKey.getWorkStart());
        query.executeUpdate();
    }

    @Override
    public void update(SpecWorkTimeKey specWorkTimeKey, SpecWorkTime specWorkTime) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE SpecWorkTime set " +
                "workStart = :specWorkStart , workEnd = :specWorkEnd where " +
                "specId = :specIdKey and workStart = :workStartKey ");
        query.setParameter("specWorkStart" , specWorkTime.getWorkStart());
        query.setParameter("specWorkEnd", specWorkTime.getWorkEnd());
        query.setParameter("specIdKey", specWorkTimeKey.getSpecId());
        query.setParameter("workStartKey", specWorkTimeKey.getWorkStart());

        query.executeUpdate();
    }

    @Override
    public List<SpecWorkTime> getBySpecId(int specId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SpecWorkTime  where specId = :specId",
                SpecWorkTime.class)
                .setParameter("specId", specId)
                .list();
    }
}
