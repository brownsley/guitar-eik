package com.chord.server.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ArtistSummary {
    Long getId();

    String getName();

    String getAvatar();

    @Value("#{target.songs.size()}")
    Integer getTotalTrack();

}
