package com.xyzcorp.httpclient;

import java.util.Map;
import java.util.stream.Collectors;
//               .stream()
//               .filter(c -> c.getLanguages().contains(language))
//               .filter(c -> c.getRegion().equals(region))
public class CountryFunctions {
    public static String findLanguagesByRegion(Map<String, Country> map,
                                               String language, String region) {
        System.out.println("Before Processing");
        String commaDelimitedString =
            map.values()
               .stream()
               .filter(c -> c.getLanguages().contains(language))
               .filter(c -> c.getRegion().equals(region))
               .map(Country::getName)
               .sorted()
               .collect(Collectors.joining(", "));
        System.out.println("This never completes");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(language);
        stringBuilder.append(" countries in ");
        stringBuilder.append(region);
        stringBuilder.append(": ");
        stringBuilder.append(commaDelimitedString);
        return stringBuilder.toString();
    }
}
