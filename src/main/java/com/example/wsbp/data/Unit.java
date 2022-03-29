package com.example.wsbp.data;

import java.io.Serializable;

public class Unit implements Serializable {
    private final int unitId;
    private final String unitName;

    public Unit(int unitId, String unitName) {
        this.unitId = unitId;
        this.unitName = unitName;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }
}
