package com.xyzcorp.textblocks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TextBlocksTest {
    String sampleJSON = """
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

    @Test
    void testTextBlockStandard() {
        var lyrics = """
            I see trees of green
            red roses too
            I see them bloom
            For me and you
            And I think to myself
            What a wonderful world""";

        Map<Character, Integer> result = Arrays.stream(lyrics.split("\n"))
            .map(String::trim)
            .flatMap(s -> Arrays.stream(s.split("\\s")))
            .collect(Collectors.groupingBy(s -> Character.toLowerCase(s.charAt(0))))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                characterListEntry -> characterListEntry.getValue().size()));

        assertThat(result).isNotEmpty();
        assertThat(result.get('t')).isEqualTo(5);
    }

    @Test
    void testTextBlockWithFormat() {
        var statement = """
            The tickets are $%2.2f which is a %%%d increase
            Be sure to get the tickets before %3$tA, %3$tB %3$te of %3$tY""";
        System.out.format(statement, 30.0, 10, LocalDate.of(2020, 7, 3));
    }

    @Test
    void textBlocksCannotBeStartedOnTheSameLineAsTripleQuotes() {
        var tripleQuoteError = """
            I will start here
            but I will have an error""";
        System.out.println(tripleQuoteError);
    }

    @Test
    void textBlocksWillRespectWhiteSpaceOnTheLeft() {
        @SuppressWarnings("SpellCheckingInspection") var jupitersMoons = """
            The planet Jupiter's four largest moons are called the Galilean
            satellites after Italian astronomer Galileo Galilei, who first
            observed them in 1610. The German astronomer Simon Marius claimed
            to have seen the moons around the same time, but he did not publish
            his observations and so Galileo is given the credit for their
            discovery. These large moons, named Io, Europa, Ganymede,
            and Callisto, are each distinctive worlds.""";
        System.out.println(jupitersMoons);
    }
}
