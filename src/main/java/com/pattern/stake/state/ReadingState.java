package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class ReadingState implements DiskState {

    @Override
    public String getName() {
        return "READING";
    }

    @Override
    public String read(Disk disk) {
        return "Disk is already reading.";
    }

    @Override
    public String write(Disk disk) {
        disk.setState(new ErrorState());
        return "Disk cannot write while reading and moved to error state.";
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        return "Disk reset from reading to idle.";
    }
}

