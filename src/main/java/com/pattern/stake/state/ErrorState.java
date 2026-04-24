package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class ErrorState implements DiskState {

    @Override
    public String getName() {
        return "ERROR";
    }

    @Override
    public String read(Disk disk) {
        String message = "Disk is in error state. Reset is required.";
        disk.addHistory("ERROR -> ERROR: " + message);
        return message;
    }

    @Override
    public String write(Disk disk, String data) {
        String message = "Disk is in error state. Reset is required.";
        disk.addHistory("ERROR -> ERROR: " + message);
        return message;
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        String message = "Disk recovered from error to idle.";
        disk.addHistory("ERROR -> IDLE: " + message);
        return message;
    }

    @Override
    public String clear(Disk disk) {
        String message = "Disk is in error state. Reset is required.";
        disk.addHistory("ERROR -> ERROR: " + message);
        return message;
    }
}

