package com.xyzcorp.records;

import org.junit.jupiter.api.Test;

public class RecordsTest {
    @Test
    void testAlbumCreation() {
        Album album = new Album("Joshua Tree",
            new Genre("Alternative"), new Artist("", "", "U2"));
        System.out.println(album.name());
    }

    @Test
    void testAlbumCreationMakeChange() {
        Album album = new Album("Joshua Tree",
            new Genre("Alternative"), new Artist("", "", "U2"));

        System.out.println(album);
        Album album1 = new Album(album.name(), new Genre("Pop"), album.artist());
        System.out.println(album1);
    }
}
