package com.chord.server.projections;

import org.springframework.beans.factory.annotation.Value;

public interface AlbumSummary {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.cover}")
    String getCover();
}