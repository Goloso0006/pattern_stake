package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class ReadingState implements DiskState {

    @Override
    public String getName() {
        return "READING";
    }

    @Override
    public String read(Disk disk) {
        String message = "Reading disk content.";
        disk.addHistory("READING -> READING: " + message);
        return message;
    }

    @Override
    public String write(Disk disk, String data) {
        disk.setState(new ErrorState());
        String message = "Cannot write while reading. Disk moved to error state.";
        disk.addHistory("READING -> ERROR: " + message);
        return message;
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        String message = "Disk reset from reading to idle.";
        disk.addHistory("READING -> IDLE: " + message);
        return message;
    }

    @Override
    public String clear(Disk disk) {
        disk.setState(new ErrorState());
        String message = "Cannot clear while reading. Disk moved to error state.";
        disk.addHistory("READING -> ERROR: " + message);
        return message;
    }
}

