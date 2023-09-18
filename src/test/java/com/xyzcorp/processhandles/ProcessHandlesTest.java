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
        current.descendants().forEach(System.out::println);
    }

    @Test
    void createProcess() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(
            new String[]{"python", "--version"});
        System.out.println(process.pid());
        Thread.sleep(10000);
    }
}
