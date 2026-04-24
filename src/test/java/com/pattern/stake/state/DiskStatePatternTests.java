package com.pattern.stake.state;

import com.pattern.stake.context.Disk;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiskStatePatternTests {

    @Test
    void diskStartsInIdleState() {
        Disk disk = new Disk();

        assertEquals("IDLE", disk.getStateName());
    }

    @Test
    void readMovesDiskFromIdleToReading() {
        Disk disk = new Disk();

        String message = disk.read();

        assertEquals("Disk started reading.", message);
        assertEquals("READING", disk.getStateName());
    }

    @Test
    void writeMovesDiskFromIdleToWriting() {
        Disk disk = new Disk();

        String message = disk.write();

        assertEquals("Disk started writing.", message);
        assertEquals("WRITING", disk.getStateName());
    }

    @Test
    void writeWhileReadingMovesDiskToError() {
        Disk disk = new Disk();
        disk.read();

        String message = disk.write();

        assertEquals("Disk cannot write while reading and moved to error state.", message);
        assertEquals("ERROR", disk.getStateName());
    }

    @Test
    void readWhileWritingMovesDiskToError() {
        Disk disk = new Disk();
        disk.write();

        String message = disk.read();

        assertEquals("Disk cannot read while writing and moved to error state.", message);
        assertEquals("ERROR", disk.getStateName());
    }

    @Test
    void resetFromErrorReturnsDiskToIdle() {
        Disk disk = new Disk();
        disk.read();
        disk.write();

        String message = disk.reset();

        assertEquals("Disk recovered from error to idle.", message);
        assertEquals("IDLE", disk.getStateName());
    }
}

