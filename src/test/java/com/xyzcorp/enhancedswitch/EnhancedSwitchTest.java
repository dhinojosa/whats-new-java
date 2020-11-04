package com.xyzcorp.enhancedswitch;

import org.junit.jupiter.api.Test;

import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EnhancedSwitchTest {
    @Test
    void testBasicSwitch() {
        var birthMonth = JANUARY;
        var result = 0;
        switch (birthMonth) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                result = 31;
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                result = 30;
                break;
            default:
                result = 29;
        }
        assertThat(result).isEqualTo(31);
    }


    @Test
    void testEnhancedSwitch() {
        var birthMonth = JANUARY;
        var result = switch (birthMonth) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            default -> 29;
        };
        System.out.println(result);
    }
}
