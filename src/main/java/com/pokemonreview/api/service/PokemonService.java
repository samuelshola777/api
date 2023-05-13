package com.pokemonreview.api.service;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.dto.request.PokemonRequest;
import com.pokemonreview.api.dto.responce.PokemonResponse;

import java.util.List;

public interface PokemonService {
   PokemonRequest createPokemon(PokemonRequest pokemonRequest);
   List<PokemonRequest> getAllPokemon(int pageNumber, int pageSize);

   Pokemon getPokemonByI(long id);

   PokemonRequest updatePokemon(PokemonRequest pokemonRequest, long id);
   void deletePokemonById(long id);

}
