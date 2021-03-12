package ru.mashka.model.entity;

import ru.mashka.model.key.SpecWorkTimeKey;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@IdClass(SpecWorkTimeKey.class)
@Table(name = "spec_work_times")
public class SpecWorkTime implements Cloneable {
    @Id
    @Column(name = "work_time_start")
    private Instant workStart;
    @Id
    @Column(name = "work_time_end")
    private Instant workEnd;
    @Id
    @Column(name = "specialist_id")
    private int specId;

    public SpecWorkTime(Instant workStart, Instant workEnd, int specId) {
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.specId = specId;
    }

    public SpecWorkTime() {

    }

    public Instant getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Instant workStart) {
        this.workStart = workStart;
    }

    public Instant getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(Instant workEnd) {
        this.workEnd = workEnd;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecWorkTime that = (SpecWorkTime) o;
        return specId == that.specId && workStart.equals(that.workStart) && workEnd.equals(that.workEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workStart, workEnd, specId);
    }

    @Override
    public SpecWorkTime clone() throws CloneNotSupportedException {
        return (SpecWorkTime) super.clone();
    }

    public SpecWorkTimeKey getSpecWorkTimeKey(SpecWorkTime specWorkTime) {
        return new SpecWorkTimeKey(
                specWorkTime.getWorkStart(), specWorkTime.getWorkEnd(), specWorkTime.getSpecId());
    }
}
