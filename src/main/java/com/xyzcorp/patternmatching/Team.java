package com.xyzcorp.patternmatching;

import java.util.Objects;

public class Team {
    private final String name;
    private final String city;
    private final int wins;
    private final int losses;

    public Team(String name, String city, int wins, int losses) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(city, "City cannot be null");
        this.name = name;
        this.city = city;
        this.wins = wins;
        this.losses = losses;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Team team) {
            return
                wins == team.wins &&
                    losses == team.losses &&
                    name.equals(team.name) &&
                    city.equals(team.city);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, wins, losses);
    }
}
