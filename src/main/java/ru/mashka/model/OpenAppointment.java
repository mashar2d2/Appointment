package ru.mashka.model;

import java.time.Instant;

public class OpenAppointment {
    private Instant openTimeStart;
    private Instant openTimeEnd;
    private int specId;

    public OpenAppointment() {
    }

    public OpenAppointment(Instant openTimeStart, Instant openTimeEnd, int specId) {
        this.openTimeStart = openTimeStart;
        this.openTimeEnd = openTimeEnd;
        this.specId = specId;
    }

    public Instant getOpenTimeStart() {
        return openTimeStart;
    }

    public void setOpenTimeStart(Instant openTimeStart) {
        this.openTimeStart = openTimeStart;
    }

    public Instant getOpenTimeEnd() {
        return openTimeEnd;
    }

    public void setOpenTimeEnd(Instant openTimeEnd) {
        this.openTimeEnd = openTimeEnd;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }
}
