package com.chord.server.services.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chord.server.dto.request.music.SongCreateDto;
import com.chord.server.entities.music.Album;
import com.chord.server.entities.music.Artist;
import com.chord.server.entities.music.Song;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.projections.SongSummary;
import com.chord.server.repositories.music.AlbumRepository;
import com.chord.server.repositories.music.ArtistRepository;
import com.chord.server.repositories.music.SongRepository;
import com.chord.server.services.storage.ImageStorageService;

@Service
public class SongService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final ImageStorageService imageStorageService;

    public SongService(SongRepository songRepository, ArtistRepository artistRepository,
            AlbumRepository albumRepository, ImageStorageService imageStorageService) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<SongSummary> searchSongs(String query) {
        return songRepository.findByTitleContainingIgnoreCase(query);
    }

    @Transactional
    public void songCreate(SongCreateDto createDto, MultipartFile file) {
        if (songRepository.existsByTitle(createDto.getTitle())) {
            throw new ResourceAlreadyExistsException(createDto.getTitle() + " already exists");
        }

        Song song = new Song();
        song.setTitle(createDto.getTitle());
        String lyric = createDto.getLyric().replace("\\n", "\n");
        song.setLyric(lyric);

        if (createDto.getAlbumId() != null) {
            Album album = albumRepository.findById(createDto.getAlbumId())
                    .orElseThrow(() -> new ResourceNotFoundException("Album Not Found"));

            song.getAlbums().add(album);
            if (!album.getSongs().contains(song)) {
                album.getSongs().add(song);
            }
        }

        if (file != null && !file.isEmpty()) {
            String cover = imageStorageService.imageUpload(file, "song");
            song.setCover(cover);
        }

        List<Artist> artists = artistRepository.findAllById(createDto.getArtists());
        if (artists.size() != createDto.getArtists().size()) {
            throw new ResourceNotFoundException("One or more Artists Not Found");
        }

        artists.forEach(artist -> {
            if (!artist.getSongs().contains(song)) {
                artist.getSongs().add(song);
            }
        });

        song.setArtists(artists);
        songRepository.save(song);
    }

    public Song getSongDetail(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song Not Found"));
    }

    public Page<SongSummary> getAllSongsSummary(int page, int count) {
        Pageable pageable = PageRequest.of(page, count, Sort.by("id").descending());
        return songRepository.findBy(pageable);
    }
}
