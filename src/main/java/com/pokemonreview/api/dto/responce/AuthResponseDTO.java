package com.pokemonreview.api.dto.responce;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponseDTO {
    private String accessToken= "Bearer ";
    @NonNull
    private String tokenType ;
}
