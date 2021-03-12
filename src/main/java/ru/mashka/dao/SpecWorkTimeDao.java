package ru.mashka.dao;

import ru.mashka.model.entity.SpecWorkTime;
import ru.mashka.model.key.SpecWorkTimeKey;

import java.time.Instant;
import java.util.List;

public interface SpecWorkTimeDao {

    List<SpecWorkTime> getBetween(int id, Instant from, Instant to);

    void add(SpecWorkTime specWorkTime);

    void delete(SpecWorkTimeKey specWorkTimeKey);

    void update(SpecWorkTimeKey specWorkTimeKey, SpecWorkTime specWorkTime);

    List<SpecWorkTime> getBySpecId(int id);
}
