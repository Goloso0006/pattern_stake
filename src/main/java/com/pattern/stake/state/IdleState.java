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
        String message = "Read operation started.";
        disk.addHistory("IDLE -> READING: " + message);
        return message;
    }

    @Override
    public String write(Disk disk, String data) {
        disk.saveContent(data);
        disk.setState(new WritingState());
        String message = "Data saved to disk.";
        disk.addHistory("IDLE -> WRITING: " + message);
        return message;
    }

    @Override
    public String reset(Disk disk) {
        disk.setState(this);
        String message = "Disk is already idle.";
        disk.addHistory("IDLE -> IDLE: " + message);
        return message;
    }

    @Override
    public String clear(Disk disk) {
        disk.clearContent();
        String message = "Disk content cleared.";
        disk.addHistory("IDLE -> IDLE: " + message);
        return message;
    }
}

