
// package com.chord.server.config;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.chord.server.entities.music.Album;
// import com.chord.server.entities.music.Artist;
// import com.chord.server.entities.music.Song;
// import com.chord.server.repositories.music.AlbumRepository;
// import com.chord.server.repositories.music.ArtistRepository;
// import com.chord.server.repositories.music.SongRepository;

// import jakarta.transaction.Transactional;
// import net.datafaker.Faker;

// @Component
// public class DataSeeder implements CommandLineRunner {

// private final ArtistRepository artistRepository;
// private final AlbumRepository albumRepository;
// private final SongRepository songRepository;

// DataSeeder(ArtistRepository artistRepository, AlbumRepository
// albumRepository, SongRepository songRepository) {
// this.artistRepository = artistRepository;
// this.albumRepository = albumRepository;
// this.songRepository = songRepository;
// }

// @Override
// @Transactional
// public void run(String... args) throws Exception {

// if (artistRepository.count() > 0)
// return;

// Faker faker = new Faker();

// List<Artist> artists = new ArrayList<>();

// for (int i = 0; i < 30; i++) {
// Artist artist = new Artist();
// artist.setName(faker.music().genre() + " " + faker.name().fullName());

// long randomId = faker.number().numberBetween(1000000L, 100000000L);
// artist.setAvatar(
// "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRx3gVDCjsgLaDv5W4bOPbCrMeOJMM119hmOz835_w1zM0ZDLt4K3GtR8_c&s=10");
// artist.setSocialLink("https://instagram.com/" + faker.internet().username());

// artists.add(artist);
// }

// artistRepository.saveAll(artists);

// List<Album> albums = new ArrayList<>();

// for (int i = 0; i < 25; i++) {
// Album album = new Album();
// album.setName(faker.music().chord() + " " + faker.commerce().productName());
// album.setCover(
// "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR30k1JtxHnqwyYeHjlfTrR7RKyCnPOcroYS8BllXUGTn-MBf5JE36c88k&s=10"
// + faker.random().nextInt() + "/300/300");

// int artistCount = faker.number().numberBetween(1, 4);
// for (int j = 0; j < artistCount; j++) {
// Artist randomArtist = artists.get(faker.random().nextInt(artists.size()));
// if (!album.getArtists().contains(randomArtist)) {
// album.getArtists().add(randomArtist);
// }
// }

// albums.add(album);
// }

// albumRepository.saveAll(albums);
// final String lyric = "[V]\n" + //
// "[C]အေးစက်နေတဲ့ငါ့ရင်ဘက်ထဲကို မင်း[Am]အလင်းတွေပက်ဖြန်းပြီး\n" +
// "[F]သက်တန့်တစ်ခုလိုဖြစ်စေခဲ့တာ [C]ကျေးဇူးတင်တ[G]ယ်\n" + //
// "[C]အပေါ်ယံအချစ်တွေနားခိုဖူးတဲ့ ငါ့[Am]နှလုံးသားဧည့်ခန်းထဲ\n" + //
// "[F]မင်းရောက်လာတော့ ခမ်းနားသွားတာ[C]ကျေးဇူးတင်တ[G]ယ်\n" + //
// "\n" +
// "[Pre]\n" + //
// "[Dm]အလုပ်တွေပင်ပန်းတယ်မထင်ဘဲ မင်း[G]အသံကြားရင်ချွေးတိတ်စေခဲ့တယ်\n" + //
// "[Dm]ငါ့အနာဂတ် ငါ့ကမ္ဘာထဲ [G]ရွှေရောင်တွေတောင်မင်း[Dm]လောက်မလှဘူးကွယ်\n" + //
// "[G]ဒဏ်ရာတွေကိုမင်းကုလိုက်တယ်\n" + //
// "\n" + //
// "[Cho]\n" + //
// "[C]မင်းလောက်ငါ့အပေါ်ကောင်းတာမရှိပါမင်း[Am]ရဲ့နားလည်မှု
// [Dm]ခွင့်လွှတ်ခြင်းတွေ\n" + //
// "သိတတ်ခြင်းတွေ ငါ့[G]ယုံကြည်ပါပြီအခု [C]မင်းစေတနာကိုနားလည်ခဲ့ချိန်\n" + //
// "[Am]ငါနောက်ထပ်မဆိုးတော့ဘူး\n" + //
// "[Dm]ငါပြောင်းလဲလိုက်ပြီ စိတ်မပျက်လိုက်ပါနဲ့ [G]နေခြည်ရယ်ထွက်မသွားနဲ့
// [Dm]ထွက်မသွားနဲ့[G]\n" + //
// "အဝေး [F] [V]\n" + //
// "[C]အေးစက်နေတဲ့ငါ့ရင်ဘက်ထဲကို မင်း[Am]အလင်းတွေပက်ဖြန်းပြီး\n" + //
// "[F]သက်တန့်တစ်ခုလိုဖြစ်စေခဲ့တာ [C]ကျေးဇူးတင်တ[G]ယ်\n" + //
// "[C]အပေါ်ယံအချစ်တွေနားခိုဖူးတဲ့ ငါ့[Am]နှလုံးသားဧည့်ခန်းထဲ\n" + //
// "[F]မင်းရောက်လာတော့ ခမ်းနားသွားတာ[C]ကျေးဇူးတင်တ[G]ယ်\n" + //
// "\n" + //
// "[Pre]\n" + //
// "[Dm]အလုပ်တွေပင်ပန်းတယ်မထင်ဘဲ မင်း[G]အသံကြားရင်ချွေးတိတ်စေခဲ့တယ်\n" + //
// "[Dm]ငါ့အနာဂတ် ငါ့ကမ္ဘာထဲ [G]ရွှေရောင်တွေတောင်မင်း[Dm]လောက်မလှဘူးကွယ်\n" + //
// "[G]ဒဏ်ရာတွေကိုမင်းကုလိုက်တယ်\n" + //
// "\n" + //
// "[Cho]\n" + //
// "[C]မင်းလောက်ငါ့အပေါ်ကောင်းတာမရှိပါမင်း[Am]ရဲ့နားလည်မှု
// [Dm]ခွင့်လွှတ်ခြင်းတွေ\n" + //
// "သိတတ်ခြင်းတွေ ငါ့[G]ယုံကြည်ပါပြီအခု [C]မင်းစေတနာကိုနားလည်ခဲ့ချိန်\n" + //
// "[Am]ငါနောက်ထပ်မဆိုးတော့ဘူး\n" + //
// "[Dm]ငါပြောင်းလဲလိုက်ပြီ စိတ်မပျက်လိုက်ပါနဲ့ [G]နေခြည်ရယ်ထွက်မသွားနဲ့
// [Dm]ထွက်မသွားနဲ့[G]\n" + //
// "အဝေး [F]";

// List<Song> songs = new ArrayList<>();

// for (Artist artist : artists) {

// int songCount = faker.number().numberBetween(8, 10);

// for (int i = 0; i < songCount; i++) {

// Song song = new Song();
// song.setTitle(faker.music().instrument() + " " + faker.lorem().word());
// song.setLyric(lyric);

// int artistCount = faker.number().numberBetween(1, 4);

// for (int j = 0; j < artistCount; j++) {
// Artist randomArtist = artists.get(faker.random().nextInt(artists.size()));
// if (!song.getArtists().contains(randomArtist)) {
// song.getArtists().add(randomArtist);
// }
// }

// if (faker.random().nextBoolean()) {
// Album randomAlbum = albums.get(faker.random().nextInt(albums.size()));
// song.setAlbum(randomAlbum);
// }

// songs.add(song);
// }
// }

// songRepository.saveAll(songs);
// albumRepository.saveAll(albums);

// System.out.println("Seeder Done: 45 Artists, 30 Albums, " + songs.size() + "
// Songs");
// }
// }