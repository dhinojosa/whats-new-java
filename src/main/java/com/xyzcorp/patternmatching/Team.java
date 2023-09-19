package com.xyzcorp.patternmatching;

import java.util.Objects;

public record Team(String city, String name, int wins, int losses) {
    public Team {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(city, "City cannot be null");
    }
}
