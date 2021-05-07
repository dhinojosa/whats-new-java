package com.xyzcorp.records;

import com.xyzcorp.Album;
import com.xyzcorp.Artist;
import com.xyzcorp.Genre;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    void testUsingRecord() {
       Album album = new Album("Purple Rain", new Genre("Pop"), new Artist("Prince"));
       String name = album.name();
       Album vanHalenRulez = album.copy("OU812");
       System.out.println(album);
       System.out.println(vanHalenRulez);
       Album album2 = new Album("Purple Rain", new Genre("Heavy Metal"), new Artist("Slayer"));

       System.out.println(album.equals(album2));
    }
}
