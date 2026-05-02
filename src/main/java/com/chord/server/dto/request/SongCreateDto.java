package com.chord.server.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SongCreateDto {
    private String title;

    private String lyric;

    private List<Long> artists;
}
