package com.pokemonreview.api.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
