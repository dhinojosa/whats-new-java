package com.xyzcorp.httpclient;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

public class CountryFunctionsTest {

    @Test
    void testFindAllEnglishSpeakingCountriesInARegion() {
        Map<String, Country> map = Map.ofEntries(
            entry("GB", new Country("Great Britain", "Europe", List.of("en"))),
            entry("AD", new Country("Andorra", "Europe", List.of("en"))),
            entry("DM", new Country("Denmark", "Europe", List.of("en", "ca"))),
            entry("AE", new Country("United Arab Emirates", "Asia", List.of("ar"))),
            entry("US", new Country("United States", "Americas", List.of("en"))),
            entry("AU", new Country("Australia", "Oceania", List.of("en")))
        );

        String result = CountryFunctions.findLanguagesByRegion(map, "en", "Europe");
        assertThat(result).isEqualTo("en countries in Europe: Andorra, Denmark, Great Britain");
    }
}
