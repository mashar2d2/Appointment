package ru.mashka.model.key;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class SpecWorkTimeKey implements Serializable {
    protected Instant workStart;
    protected int specId;

    public SpecWorkTimeKey(Instant workStart, Instant workEnd, int specId) {
        this.workStart = workStart;
        this.specId = specId;
    }

    public SpecWorkTimeKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecWorkTimeKey that = (SpecWorkTimeKey) o;
        return specId == that.specId && Objects.equals(workStart, that.workStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workStart, specId);
    }

    public Instant getWorkStart() {
        return workStart;
    }

    public int getSpecId() {
        return specId;
    }
}
