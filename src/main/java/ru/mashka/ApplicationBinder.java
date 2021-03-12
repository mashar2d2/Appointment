package ru.mashka;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.dialect.SQLiteDialect;
import org.reflections.Reflections;
import org.sqlite.JDBC;

import ru.mashka.dao.AppointmentDao;
import ru.mashka.dao.SpecWorkTimeDao;
import ru.mashka.dao.SpecialistDao;
import ru.mashka.dao.impl.AppointmentDaoImpl;
import ru.mashka.dao.impl.SpecWorkTimeDaoImpl;
import ru.mashka.dao.impl.SpecialistDaoImpl;
import ru.mashka.model.entity.Appointment;

import javax.persistence.Entity;
import java.util.Set;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(SpecialistDaoImpl.class).to(SpecialistDao.class);
        bind(AppointmentDaoImpl.class).to(AppointmentDao.class);
        bind(SpecWorkTimeDaoImpl.class).to(SpecWorkTimeDao.class);
        bind(buildSessionFactory()).to(SessionFactory.class);
    }

    public SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        getAnnotatedClasses().forEach(configuration::addAnnotatedClass);
        String defaultDb = this.getClass().getClassLoader().getResource("identifier.sqlite").getFile();
        return configuration
                .setProperty(AvailableSettings.DRIVER, JDBC.class.getName())
                .setProperty(AvailableSettings.URL, System.getProperty("jdbcUrl", "jdbc:sqlite:" + defaultDb))
                .setProperty(AvailableSettings.SHOW_SQL, Boolean.TRUE.toString())
                .setProperty(AvailableSettings.DIALECT, SQLiteDialect.class.getName())
                .setProperty(AvailableSettings.JDBC_TIME_ZONE, "UTC")
                .setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, ThreadLocalSessionContext.class.getName())
                .buildSessionFactory();
    }

    public Set<Class<?>> getAnnotatedClasses() {
        Reflections reflections = new Reflections(Appointment.class.getPackage().getName());
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Entity.class);
        return typesAnnotatedWith;
    }
}
