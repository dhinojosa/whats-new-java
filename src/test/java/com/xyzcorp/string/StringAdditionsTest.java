package com.xyzcorp.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StringAdditionsTest {

    /**
     * Returns a string whose value is the concatenation of this string
     * repeated count times.
     * If this string is empty or count is zero then the empty string is
     * returned.
     */
    @Test
    void testRepeat() {
        assertThat("Hello".repeat(3)).isEqualTo("HelloHelloHello");
    }

    /**
     * Returns a string whose value is this string, with all leading and
     * trailing white space removed.
     * If this String object represents an empty string, or if all code
     * points in this string are white space, then an empty string is returned.
     */
    @Test
    void testStrip() {
        assertThat("  Hello  ".strip()).isEqualTo("Hello");
    }

    /**
     * trim Returns a string whose value is this string, with all leading and
     * trailing space removed, where space is defined as any character whose
     * codepoint is less than or equal to 'U+0020' (the space character).
     * <p>
     * strips whitespace determines if the specified character
     * (Unicode code point) is white
     * space according to Java. A character is a Java whitespace character if
     * and only if it satisfies one of the following criteria:
     * It is a Unicode space character (SPACE_SEPARATOR, LINE_SEPARATOR, or
     * PARAGRAPH_SEPARATOR) but is not also a non-breaking space ('\u00A0',
     * '\u2007', '\u202F').
     * It is '\t', U+0009 HORIZONTAL TABULATION.
     * It is '\n', U+000A LINE FEED.
     * It is '\u000B', U+000B VERTICAL TABULATION.
     * It is '\f', U+000C FORM FEED.
     * It is '\r', U+000D CARRIAGE RETURN.
     * It is '\u001C', U+001C FILE SEPARATOR.
     * It is '\u001D', U+001D GROUP SEPARATOR.
     * It is '\u001E', U+001E RECORD SEPARATOR.
     * It is '\u001F', U+001F UNIT SEPARATOR
     */
    @Test
    void testDifferenceBetweenStripAndTrim() {
        assertThat("  Hello  ".strip()).isEqualTo("Hello");
        assertThat("  Hello  ".trim()).isEqualTo("Hello");
    }

    /**
     * Returns true if the string is empty or contains only white space
     * codepoints see above, otherwise false.
     */
    @Test
    void testIsBlank() {
        assertThat("\t\n   ".isBlank()).isTrue();
    }

    @Test
    void testLines() {
        var lines = """
            Come with me
            And you'll be
            In a world of pure imagination
            Take a look
            And you'll see
            Into your imagination
            We'll begin
            With a spin
            Traveling in
            The world of my creation
            What we'll see
            Will defy
            Explanation""";

        Map<String, List<String>> collect = lines
            .lines()
            .map(String::toLowerCase)
            .flatMap(s -> Arrays.stream(s.split(" ")))
            .distinct()
            .collect(Collectors.groupingBy(s -> String.valueOf(s.charAt(0))));

        assertThat(collect.get("w")).contains("we'll", "will", "what");
    }
}
