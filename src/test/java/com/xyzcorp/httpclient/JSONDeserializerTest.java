package com.xyzcorp.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONDeserializerTest {
    @Test
    public void testDeserializationOfJson() throws JsonProcessingException {
        var json = """
            {
                "AD": {
                    "currency": {
                        "primary": "EUR"
                    },
                    "iso": {
                        "code2": "AD",
                        "code3": "AND",
                        "num": "020"
                    },
                    "languages": [
                        "ca"
                    ],
                    "name": "Andorra",
                    "region": "Europe"
                },
                "AE": {
                    "currency": {
                        "primary": "AED"
                    },
                    "iso": {
                        "code2": "AE",
                        "code3": "ARE",
                        "num": "784"
                    },
                    "languages": [
                        "ar"
                    ],
                    "name": "United Arab Emirates",
                    "region": "Asia"
                }
           }""";

        Map<String, Country> stringCountryMap =
            JSONDeserializer.processJson(json);
        assertThat(stringCountryMap.size()).isEqualTo(2);
    }
}
