package ru.mashka.dao;

import ru.mashka.model.entity.Specialist;

public interface SpecialistDao {

    Specialist getById(int id);

    void add(Specialist specialist);

    void delete(int id);

    void update(Specialist specialist);
}
