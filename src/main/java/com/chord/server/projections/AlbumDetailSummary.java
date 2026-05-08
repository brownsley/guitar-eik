package com.chord.server.projections;

import java.util.List;

public interface AlbumDetailSummary {
    Long getId();

    String getName();

    String getCover();

    List<SongShrotInfo> getSongs();

    List<ArtistSummary> getArtists();

    public interface SongShrotInfo {
        Long getId();

        String getTitle();

        Long getTotalView();

        List<ArtistNameOnly> getArtists();

        interface ArtistNameOnly {
            String getName();
        }

    }

    public interface ArtistSummary {
        Long getId();

        String getName();

        String getAvatar();
    }
}
