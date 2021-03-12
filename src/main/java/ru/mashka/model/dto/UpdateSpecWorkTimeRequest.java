package ru.mashka.model.dto;

import ru.mashka.model.entity.SpecWorkTime;
import ru.mashka.model.key.SpecWorkTimeKey;

public class UpdateSpecWorkTimeRequest {

    private SpecWorkTimeKey specWorkTimeKey;
    private SpecWorkTime specWorkTime;

    public UpdateSpecWorkTimeRequest(SpecWorkTimeKey specWorkTimeKey, SpecWorkTime specWorkTime) {
        this.specWorkTimeKey = specWorkTimeKey;
        this.specWorkTime = specWorkTime;
    }

    public UpdateSpecWorkTimeRequest() {
    }

    public SpecWorkTimeKey getSpecWorkTimeKey() {
        return specWorkTimeKey;
    }

    public void setSpecWorkTimeKey(SpecWorkTimeKey specWorkTimeKey) {
        this.specWorkTimeKey = specWorkTimeKey;
    }

    public SpecWorkTime getSpecWorkTime() {
        return specWorkTime;
    }

    public void setSpecWorkTime(SpecWorkTime specWorkTime) {
        this.specWorkTime = specWorkTime;
    }
}
