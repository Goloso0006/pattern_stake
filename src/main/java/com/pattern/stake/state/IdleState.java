package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class IdleState implements DiskState {

    @Override
    public String getName() {
        return "IDLE";
    }

    @Override
    public String read(Disk disk) {
        disk.setState(new ReadingState());
        return "Disk started reading.";
    }

    @Override
    public String write(Disk disk) {
        disk.setState(new WritingState());
        return "Disk started writing.";
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(this);
        return "Disk is already idle.";
    }
}

