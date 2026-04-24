package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class ErrorState implements DiskState {

    @Override
    public String getName() {
        return "ERROR";
    }

    @Override
    public String read(Disk disk) {
        return "Disk is in error state. Reset is required.";
    }

    @Override
    public String write(Disk disk) {
        return "Disk is in error state. Reset is required.";
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        return "Disk recovered from error to idle.";
    }
}

