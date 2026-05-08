
package com.chord.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.chord.server.entities.Album;
import com.chord.server.entities.Artist;
import com.chord.server.entities.Song;
import com.chord.server.repositories.AlbumRepository;
import com.chord.server.repositories.ArtistRepository;
import com.chord.server.repositories.SongRepository;

import jakarta.transaction.Transactional;
import net.datafaker.Faker;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (artistRepository.count() == 0) {
            Faker faker = new Faker();

            List<Artist> artists = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Artist artist = new Artist();
                artist.setName(faker.music().genre() + " " + faker.name().fullName());
                long randomId = faker.number().numberBetween(1000000L, 100000000L);
                artist.setAvatar("https://avatars.githubusercontent.com/u/" + randomId + "?v=4");
                artist.setSocialLink("https://instagram.com/" + faker.internet().username());
                artists.add(artist);
            }
            artistRepository.saveAll(artists);

            List<Album> albums = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                Album album = new Album();
                album.setName(faker.music().chord() + " " + faker.commerce().productName());
                album.setCover("https://picsum.photos/seed/" + faker.random().nextInt() + "/300/300");

                int artistCountPerAlbum = faker.number().numberBetween(1, 4);
                for (int j = 0; j < artistCountPerAlbum; j++) {
                    Artist randomArtist = artists.get(faker.random().nextInt(artists.size()));
                    if (!album.getArtists().contains(randomArtist)) {
                        album.getArtists().add(randomArtist);
                    }
                }
                albums.add(album);
            }
            albumRepository.saveAll(albums);

            final String[] REAL_LYRICS = {
                    "[Intro]\nG | C | D | G\n\n[Verse 1]\nG          C\nနေဝင်သွားတဲ့ အချိန်တိုင်းမှာ...",
                    "[Intro]\nAm | F | C | G\n\n[Verse 1]\nAm         F\nYesterday is far away...",
                    "[Intro]\nC | Em | F | G\n\n[Chorus]\nC          Em\nချစ်သူ သိစေချင်တယ်..."
            };

            List<Song> allSongs = new ArrayList<>();
            for (Artist artist : artists) {
                int songCountForThisArtist = faker.number().numberBetween(8, 16);

                for (int s = 0; s < songCountForThisArtist; s++) {
                    Song song = new Song();
                    song.setTitle(faker.music().instrument() + " " + faker.lorem().word());
                    song.setLyric(REAL_LYRICS[faker.random().nextInt(REAL_LYRICS.length)]);

                    song.getArtists().add(artist);

                    if (faker.random().nextBoolean()) {
                        Album randomAlbum = albums.get(faker.random().nextInt(albums.size()));
                        song.setAlbum(randomAlbum);

                        if (!randomAlbum.getArtists().contains(artist)) {
                            randomAlbum.getArtists().add(artist);
                        }
                    }
                    allSongs.add(song);
                }
            }

            songRepository.saveAll(allSongs);
            albumRepository.saveAll(albums);

            System.out.println("Seeding Complete: 30 Artists, 25 Albums, " + allSongs.size() + " Songs!");
        }
    }
}