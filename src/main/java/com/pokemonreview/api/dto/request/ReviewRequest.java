package com.pokemonreview.api.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long id;
    private String title;
    private String content;
    private String start;
}
