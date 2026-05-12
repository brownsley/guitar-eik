package com.chord.server.projections;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface AlbumDetailSummary {
    Long getId();

    String getName();

    String getCover();

    @Value("#{target.songs.size()}")
    Integer getTotalSongs();

    List<SongShrotInfo> getSongs();

    List<ArtistSummary> getArtists();

    public interface SongShrotInfo {
        Long getId();

        String getTitle();

        Long getTotalView();

        List<ArtistSummary> getArtists();

    }

    public interface ArtistSummary {
        Long getId();

        String getName();

        String getAvatar();
    }
}
