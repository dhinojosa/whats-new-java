package com.xyzcorp.enhancedswitch;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Month;

import org.junit.jupiter.api.Test;

public class EnhancedSwitchTest {

    private final Month birthMonth = JANUARY;

    @SuppressWarnings("EnhancedSwitchMigration")
    @Test
    void testBasicSwitch() {
        int result = 0;
        switch (birthMonth) { //switch statement
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
    void testEnhancedSwitchWithYield() {
        final var result = switch (birthMonth) {  //expression
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER:
                yield 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER:
                yield 30;
            default:
                yield 29;
        };
        System.out.println(result);
    }

    @Test
    void testEnhancedSwitch() {
        final var result = switch (birthMonth) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            default -> 29;
        };
        System.out.println(result);
    }

    @Test
    void testPerformingActionWithinACase() {
        var result = switch (birthMonth) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER:
                System.out.println("Did something first");
                yield 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER:
                System.out.println("Days that have 30 days");
                yield 30;
            default:
                yield 28;
        };
        assertThat(result).isEqualTo(31);
    }
}
