package com.xyzcorp;


import java.util.Objects;

public record Album(String name, Genre genre, Artist artist){
    public Album {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(genre, "Genre cannot be null");
        Objects.requireNonNull(artist, "Artist cannot be null");
    }

    public Album(String name, Genre genre) {
        this(name, genre, new Artist("U2"));
    }

    public Album copy(String name) {
        return new Album(name, genre, artist);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Album other) {
            return this.name.equals(other.name());
        }
        return false;
    }
}

