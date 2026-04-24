package com.pattern.stake.context;

import com.pattern.stake.state.DiskState;

public class Disk {

    private DiskState state;

    public Disk() {
    }

    public Disk(DiskState state) {
        this.state = state;
    }

    public DiskState getState() {
        return state;
    }

    public void setState(DiskState state) {
        this.state = state;
    }

    public String getStateName() {
        return state == null ? "NO_STATE" : state.getName();
    }
}

