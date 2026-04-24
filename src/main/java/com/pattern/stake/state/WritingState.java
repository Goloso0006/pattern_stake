package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public class WritingState implements DiskState {

    @Override
    public String getName() {
        return "WRITING";
    }

    @Override
    public String read(Disk disk) {
        disk.setState(new ErrorState());
        return "Disk cannot read while writing and moved to error state.";
    }

    @Override
    public String write(Disk disk) {
        return "Disk is already writing.";
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        return "Disk reset from writing to idle.";
    }
}

