package com.pattern.stake.state;

import com.pattern.stake.context.Disk;

public interface DiskState {

    String getName();

    String read(Disk disk);

    String write(Disk disk, String data);

    String reset(Disk disk);

    String clear(Disk disk);
}

