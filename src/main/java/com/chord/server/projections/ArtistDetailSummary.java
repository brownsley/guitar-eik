package com.chord.server.projections;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface ArtistDetailSummary {
    Long getId();

    String getName();

    String getAvatar();

    @Value("#{target.songs.size()}")
    Integer getTotalTrack();

    List<SongShortInfo> getSongs();

    public interface SongShortInfo {
        Long getId();

        String getTitle();

        Long getTotalView();
    }
}
