package com.pokemonreview.api.service;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.data.repository.PokemonRepository;
import com.pokemonreview.api.dto.request.PokemonRequest;
import com.pokemonreview.api.dto.responce.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{

    private PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonRequest createPokemon(PokemonRequest pokemonRequest) {

        Pokemon pokemon = new Pokemon();

        pokemon.setName(pokemonRequest.getName());
        pokemon.setType(pokemonRequest.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);


       PokemonRequest pokemonRequest1 = new PokemonRequest();
        pokemonRequest1.setId(newPokemon.getId());
        pokemonRequest1.setName(newPokemon.getName());
        pokemonRequest1.setType(newPokemon.getType());
        return pokemonRequest1;

    }

    @Override
    public List<PokemonRequest> getAllPokemon() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons
                .stream()
                .map(po -> mapToDto(po))
                .collect(Collectors.toList());
    }


    private Pokemon mapToEntity(PokemonRequest pokemonRequest){

      Pokemon pokemon   = new Pokemon();
        pokemon.setName(pokemonRequest.getName());
        pokemon.setType(pokemonRequest.getType());
        return pokemon;

    }

    private PokemonRequest mapToDto(Pokemon pokemon){
        PokemonRequest pokemonRequest = new PokemonRequest();
        pokemonRequest.setName(pokemon.getName());
        pokemonRequest.setType(pokemon.getType());
        pokemonRequest.setId(pokemon.getId());
        return pokemonRequest;
    }
}
