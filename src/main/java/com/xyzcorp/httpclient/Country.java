package com.xyzcorp.httpclient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties({"currency", "iso"})
public class Country {
    private final String name;
    private final String region;
    private final List<String> languages;

    @JsonCreator
    public Country(@JsonProperty("name") String name,
                   @JsonProperty("region") String region,
                   @JsonProperty("languages") List<String> languages) {
        this.name = name;
        this.region = region;
        this.languages = languages;
    }


    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public List<String> getLanguages() {
        return languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name) &&
            region.equals(country.region) &&
            languages.equals(country.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, languages);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Country.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("region='" + region + "'")
            .add("languages=" + languages)
            .toString();
    }
}
