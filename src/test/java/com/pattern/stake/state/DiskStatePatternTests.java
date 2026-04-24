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

        assertEquals("Read operation started.", message);
        assertEquals("READING", disk.getStateName());
    }

    @Test
    void writeMovesDiskFromIdleToWriting() {
        Disk disk = new Disk();

        String message = disk.write("test-content");

        assertEquals("Data saved to disk.", message);
        assertEquals("WRITING", disk.getStateName());
        assertEquals("test-content", disk.getContent());
    }

    @Test
    void writeWhileReadingMovesDiskToError() {
        Disk disk = new Disk();
        disk.read();

        String message = disk.write("blocked");

        assertEquals("Cannot write while reading. Disk moved to error state.", message);
        assertEquals("ERROR", disk.getStateName());
    }

    @Test
    void readWhileWritingMovesDiskToError() {
        Disk disk = new Disk();
        disk.write("first");

        String message = disk.read();

        assertEquals("Cannot read while writing. Disk moved to error state.", message);
        assertEquals("ERROR", disk.getStateName());
    }

    @Test
    void resetFromErrorReturnsDiskToIdle() {
        Disk disk = new Disk();
        disk.read();
        disk.write("blocked");

        String message = disk.reset();

        assertEquals("Disk recovered from error to idle.", message);
        assertEquals("IDLE", disk.getStateName());
    }

    @Test
    void clearContentWorksInIdleState() {
        Disk disk = new Disk();
        disk.write("some-data");
        disk.reset();

        String message = disk.clear();

        assertEquals("Disk content cleared.", message);
        assertEquals("", disk.getContent());
    }
}

