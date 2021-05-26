package com.xyzcorp.usingswitch;

import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    void testSwitch() {
        String month = "January";
        int result = getDaysFrom(month);
    }

    private int getDaysFrom(String month) {
        return switch (month) {
            case "January", "March", "May", "July", "August", "October",
                "December" -> 31;
            case "April", "June", "September", "November" -> 30;
            default -> 29;
        };
    }
}
