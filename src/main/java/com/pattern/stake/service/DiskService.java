package com.pattern.stake.service;

import com.pattern.stake.context.Disk;
import com.pattern.stake.controller.DiskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiskService {

    private final Disk disk;

    public DiskService() {
        this.disk = new Disk();
    }

    public DiskResponse getCurrentStateResponse() {
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                "Current state retrieved.",
                disk.getContent(),
                disk.getHistory()
        );
    }

    public DiskResponse read() {
        String message = disk.read();
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                message,
                disk.getContent(),
                disk.getHistory()
        );
    }

    public DiskResponse write(String data) {
        String message = disk.write(data);
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                message,
                null,
                disk.getHistory()
        );
    }

    public DiskResponse reset() {
        String message = disk.reset();
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                message,
                null,
                disk.getHistory()
        );
    }

    public DiskResponse clear() {
        String message = disk.clear();
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                message,
                disk.getContent(),
                disk.getHistory()
        );
    }

    public DiskResponse getHistory() {
        List<String> history = disk.getHistory();
        return new DiskResponse(
                formatStateName(disk.getStateName()),
                "History retrieved.",
                null,
                history
        );
    }

    private String formatStateName(String stateName) {
        return stateName.substring(0, 1).toUpperCase() + stateName.substring(1).toLowerCase();
    }
}

