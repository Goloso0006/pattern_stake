package com.pattern.stake.service;

import com.pattern.stake.context.Disk;
import com.pattern.stake.controller.DiskResponse;
import com.pattern.stake.state.DiskState;
import org.springframework.stereotype.Service;

@Service
public class DiskService {

    private final Disk disk;

    public DiskService() {
        this.disk = new Disk();
    }

    public Disk getDisk() {
        return disk;
    }

    public DiskState getCurrentState() {
        return disk.getState();
    }

    public DiskResponse getCurrentStateResponse() {
        return new DiskResponse(formatStateName(disk.getStateName()), "Current state retrieved.");
    }

    public DiskResponse read() {
        String message = disk.read();
        return new DiskResponse(formatStateName(disk.getStateName()), message);
    }

    public DiskResponse write() {
        String message = disk.write();
        return new DiskResponse(formatStateName(disk.getStateName()), message);
    }

    public DiskResponse reset() {
        String message = disk.reset();
        return new DiskResponse(formatStateName(disk.getStateName()), message);
    }

    public void changeState(DiskState state) {
        disk.setState(state);
    }

    private String formatStateName(String stateName) {
        return stateName.substring(0, 1).toUpperCase() + stateName.substring(1).toLowerCase();
    }
}

