package com.xyzcorp.processhandle;

import org.junit.jupiter.api.Test;

public class ProcessHandleTest {

    @Test
    void testProcessHandle() {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
    }
}
