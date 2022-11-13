package com.xyzcorp.processhandles;

import org.junit.jupiter.api.Test;

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
}
