package com.chord.server.projections;

import java.util.List;

public interface SongSummary {
    Long getId();

    String getTitle();

    Long getTotalView();

    List<ArtistSummary> getArtists();

    interface ArtistSummary {
        String getName();
    }
}
