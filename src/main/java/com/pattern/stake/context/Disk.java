package com.pattern.stake.context;

import com.pattern.stake.state.DiskState;
import com.pattern.stake.state.IdleState;

import java.util.ArrayList;
import java.util.List;

public class Disk {

    private DiskState state = new IdleState();
    private String content = "";
    private final List<String> history = new ArrayList<>();

    public Disk() {
        addHistory("Disk initialized in state: " + state.getName());
    }

    public Disk(DiskState state) {
        this.state = state;
        addHistory("Disk initialized in state: " + state.getName());
    }

    public DiskState getState() {
        return state;
    }

    public void setState(DiskState state) {
        this.state = state;
    }

    public String read() {
        return state.read(this);
    }

    public String write(String data) {
        return state.write(this, data);
    }

    public String reset() {
        return state.reset(this);
    }

    public String clear() {
        return state.clear(this);
    }

    public String getStateName() {
        return state.getName();
    }

    public void saveContent(String data) {
        this.content = data == null ? "" : data;
    }

    public String getContent() {
        return content;
    }

    public void clearContent() {
        this.content = "";
    }

    public void addHistory(String message) {
        history.add(message);
    }

    public List<String> getHistory() {
        return List.copyOf(history);
    }
}

