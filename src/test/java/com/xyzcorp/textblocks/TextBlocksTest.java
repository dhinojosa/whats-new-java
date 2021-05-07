package com.xyzcorp.textblocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class TextBlocksTest {

    @Test
    void testSimpleJsonCountry() {
        var payload = """
            [
                {
                    "country": "Afghanistan",
                    "city": "Kabul"
                },
                {
                    "country": "Albania",
                    "city": "Tirana"
                },
                {
                    "country": "Algeria",
                    "city": "Alger"
                }
            ]""";

        System.out.println(payload);
    }
}
