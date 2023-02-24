package com.xyzcorp.processhandles;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ProcessHandlesTest {
    @Test
    void processHandlesTest() {
        System.out.printf("Process Starting on %s%n", ProcessHandle.current().pid());
    }

    @Test
    void processDescendentsTest() {
        ProcessHandle current = ProcessHandle.current();
        System.out.printf("Process Descendents %s%n", current.descendants());
    }

    @Test
    void createProcess() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[]{"python", "-version"});
        System.out.println(process.pid());
        Thread.sleep(10000);
    }
}
