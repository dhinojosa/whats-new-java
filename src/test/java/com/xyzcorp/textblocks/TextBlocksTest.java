package com.xyzcorp.textblocks;

import org.junit.jupiter.api.Test;

public class TextBlocksTest {
    @Test
    void testParseJsonWithTwoCountries() {
        String payload = """
            [
               { "code" : "AF", "name" : "Afghanistan" },
               { "code" : "AX", "name" : "Åland Islands" }
             ]""";

        System.out.println(payload);
    }

    @Test
    void testWithFormattedAndQuotes() {
        String payload = """
            [
                {
                    "country": "%s",
                    "city": "Kabul"
                },
                {
                    "country": "Albania",
                    "city": "Tirana"
                },
            ]""".formatted("Afghanistan");
        System.out.println(payload);
    }
}
