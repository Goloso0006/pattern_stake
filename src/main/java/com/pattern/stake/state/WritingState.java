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
        String message = "Cannot read while writing. Disk moved to error state.";
        disk.addHistory("WRITING -> ERROR: " + message);
        return message;
    }

    @Override
    public String write(Disk disk, String data) {
        disk.saveContent(data);
        String message = "Data updated while writing.";
        disk.addHistory("WRITING -> WRITING: " + message);
        return message;
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(new IdleState());
        String message = "Disk reset from writing to idle.";
        disk.addHistory("WRITING -> IDLE: " + message);
        return message;
    }

    @Override
    public String clear(Disk disk) {
        disk.clearContent();
        String message = "Disk content cleared while writing.";
        disk.addHistory("WRITING -> WRITING: " + message);
        return message;
    }
}

