package com.pokemonreview.api.dto.request;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PokemonRequest {

    private Long id;
    private String name;
    private String type;

    public static void main(String[] args) {
        PokemonRequest pokemonRequest = new PokemonRequest();
        pokemonRequest.setId(1L);
        pokemonRequest.setName("Bulbasaur");
        pokemonRequest.setType("grass");
//        System.out.println(pokemonRequest);
        System.out.println(pokemonRequest.getName()+"  is samuel shoal");

    }
}
