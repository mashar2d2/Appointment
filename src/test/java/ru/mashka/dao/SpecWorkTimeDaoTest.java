package ru.mashka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.mashka.injection.InjectionExtension;
import ru.mashka.model.entity.SpecWorkTime;
import ru.mashka.model.key.SpecWorkTimeKey;
import ru.mashka.utils.DateUtils;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;

@ExtendWith(InjectionExtension.class)
public class SpecWorkTimeDaoTest {

    @Inject
    private SpecWorkTimeDao specWorkTimeDao;

    @Inject
    private SessionFactory sessionFactory;

    @Test
    void test() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("09.03.2021 15:00");
            Instant instant2 = DateUtils.parse("09.03.2021 17:00");
            Instant instant3 = DateUtils.parse("11.03.2021 15:00");
            Instant instant4 = DateUtils.parse("11.03.2021 17:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant1, instant2, 1);
            specWorkTimeDao.add(specWorkTime);
            SpecWorkTime specWorkTime1 = new SpecWorkTime(instant3, instant4, 1);
            specWorkTimeDao.add(specWorkTime1);
            List<SpecWorkTime> between = specWorkTimeDao.getBetween(1, instant1, instant4);

            Assertions.assertEquals(2, between.size());
            Assertions.assertTrue(between.contains(specWorkTime), "true? ");
            Assertions.assertTrue(between.contains(specWorkTime1), "or not? ");
            tr.rollback();
        }
    }

    @Test
    void test2() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("12.03.2021 13:00");
            Instant instant2 = DateUtils.parse("12.03.2021 17:00");
            Instant instant3 = DateUtils.parse("12.03.2021 16:00");
            Instant instant4 = DateUtils.parse("12.03.2021 15:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant1, instant2, 1);
            specWorkTimeDao.add(specWorkTime);

            List<SpecWorkTime> between = specWorkTimeDao.getBetween(1, instant4, instant3);
            Assertions.assertTrue(between.contains(specWorkTime));
            tr.rollback();
        }
    }

    @Test
    void test3() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("13.03.2021 13:00");
            Instant instant2 = DateUtils.parse("13.03.2021 17:00");
            Instant instant3 = DateUtils.parse("13.03.2021 18:00");
            Instant instant4 = DateUtils.parse("13.03.2021 19:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant1, instant3, 1);
            specWorkTimeDao.add(specWorkTime);

            List<SpecWorkTime> between = specWorkTimeDao.getBetween(1, instant2, instant4);
            Assertions.assertTrue(between.contains(specWorkTime));
            tr.rollback();
        }
    }

    @Test
    void test4() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            Instant instant1 = DateUtils.parse("14.03.2021 12:00");
            Instant instant2 = DateUtils.parse("14.03.2021 13:00");
            Instant instant3 = DateUtils.parse("14.03.2021 14:00");
            Instant instant4 = DateUtils.parse("14.03.2021 18:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant1, instant3, 1);
            specWorkTimeDao.add(specWorkTime);

            List<SpecWorkTime> between = specWorkTimeDao.getBetween(1, instant2, instant4);
            Assertions.assertTrue(between.contains(specWorkTime));
            tr.rollback();
        }
    }

    @Test
    void test5() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();

            Instant instant1 = DateUtils.parse("15.03.2021 12:00");
            Instant instant2 = DateUtils.parse("15.03.2021 13:00");
            Instant instant3 = DateUtils.parse("15.03.2021 14:00");
            Instant instant4 = DateUtils.parse("15.03.2021 18:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant2, instant3, 1);
            specWorkTimeDao.add(specWorkTime);

            List<SpecWorkTime> between = specWorkTimeDao.getBetween(1, instant1, instant4);
            Assertions.assertTrue(between.contains(specWorkTime));
            tr.rollback();
        }
    }

    @Test
    void test6() throws CloneNotSupportedException {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tr = session.beginTransaction();

            Instant instant1 = DateUtils.parse("15.03.2021 12:00");
            Instant instant2 = DateUtils.parse("15.03.2021 13:00");
            Instant instant3 = DateUtils.parse("15.03.2021 14:00");
            Instant instant4 = DateUtils.parse("15.03.2021 18:00");

            SpecWorkTime specWorkTime = new SpecWorkTime(instant1, instant2, 1);
            specWorkTimeDao.add(specWorkTime);

            specWorkTimeDao.getBySpecId(1);

            SpecWorkTimeKey specWorkTimeKey = specWorkTime.getSpecWorkTimeKey(specWorkTime);

            SpecWorkTime cloneSpecWorkTime = specWorkTime.clone();
            Assertions.assertEquals(cloneSpecWorkTime, specWorkTime);

            cloneSpecWorkTime.setWorkStart(instant3);
            cloneSpecWorkTime.setWorkEnd(instant4);

            specWorkTimeDao.update(specWorkTimeKey, cloneSpecWorkTime);

            specWorkTimeDao.delete(specWorkTimeKey);
            tr.rollback();
        }
    }
}
