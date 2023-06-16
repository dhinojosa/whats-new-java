package com.xyzcorp.records;

import com.xyzcorp.patternmatching.Team;
import org.assertj.core.api.Assertions;
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

    @Test
    void testCreatingRecordDefinitionInsideOfMethod() {
        record Team(String name, String city){}
        var team = new Team("Seattle", "Mariners");
        Assertions.assertThat(team.name()).isEqualTo("Seattle");
    }

    @Test
    void testExtendingByComposition() {
        record Teamz(String name, String city){
            Teamz(String name) {
                this(name, "Unknown");
            }
        }
        record BaseballTeam(Teamz teamz, String startingPitcher) {
            public static BaseballTeam of (String name, String city, String startingPitcher) {
                return new BaseballTeam(new Teamz(name, city), startingPitcher);
            }
        }

        var baseballTeam = BaseballTeam.of("Baltimore", "Orioles", "Bob Awesome");
        Assertions.assertThat(baseballTeam.teamz().city()).isEqualTo("Seattle");
    }
}
