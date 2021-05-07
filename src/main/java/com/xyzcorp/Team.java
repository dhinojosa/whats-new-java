package com.xyzcorp;

public class Team {
    private String name;
    private int wins;
    private int losses;


    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Team other) {
            return this.name.equals(other.name);
        }
        return false;
    }
}
