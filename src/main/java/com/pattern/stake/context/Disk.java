package com.pattern.stake.context;

import com.pattern.stake.state.DiskState;
import com.pattern.stake.state.IdleState;

public class Disk {

    private DiskState state = new IdleState();

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

    public String read() {
        return state.read(this);
    }

    public String write() {
        return state.write(this);
    }

    public String reset() {
        return state.reset(this);
    }

    public String getStateName() {
        return state.getName();
    }
}

