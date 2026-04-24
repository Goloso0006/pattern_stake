package com.pattern.stake.service;

import com.pattern.stake.context.Disk;
import com.pattern.stake.state.DiskState;
import com.pattern.stake.state.IdleState;
import org.springframework.stereotype.Service;

@Service
public class DiskService {

    private final Disk disk;

    public DiskService() {
        this.disk = new Disk(new IdleState());
    }

    public Disk getDisk() {
        return disk;
    }

    public DiskState getCurrentState() {
        return disk.getState();
    }

    public String getCurrentStateName() {
        return disk.getStateName();
    }

    public String read() {
        return disk.read();
    }

    public String write() {
        return disk.write();
    }

    public String reset() {
        return disk.reset();
    }

    public void changeState(DiskState state) {
        disk.setState(state);
    }
}

