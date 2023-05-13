package com.pokemonreview.api.service;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.data.repository.PokemonRepository;
import com.pokemonreview.api.dto.request.PokemonRequest;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {


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
    public List<PokemonRequest> getAllPokemon(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page <Pokemon> pokemons = pokemonRepository.findAll(pageable);
      List<Pokemon> listOfPokemons = pokemons.getContent();
        return listOfPokemons
                .stream()
                .map(po -> mapToDto(po))
                .collect(Collectors.toList());
    }

    @Override
    public Pokemon getPokemonByI(long id) {
        return pokemonRepository.findById(id).
        orElseThrow(() -> new PokemonNotFoundException
        ("pokemon not found"));
    }

    @Override
    public PokemonRequest updatePokemon(PokemonRequest pokemonRequest, long id) {
    Pokemon foundPokemon =
    pokemonRepository.findById(id).orElseThrow(
    () -> new PokemonNotFoundException
    ("pokemon could not be Updated"));

    foundPokemon.setName(pokemonRequest.getName());
    foundPokemon.setType(pokemonRequest.getType());
    Pokemon updatePokemon = pokemonRepository.save(foundPokemon);
        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemonById(long id) {
    Pokemon deletePokemon =
    pokemonRepository.findById(id).orElseThrow
    (() -> new PokemonNotFoundException
    ("unable to delete because pokemon could'nt be found"));
      pokemonRepository.delete(deletePokemon);
    }


    private Pokemon mapToEntity(PokemonRequest pokemonRequest) {

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonRequest.getName());
        pokemon.setType(pokemonRequest.getType());
        return pokemon;

    }

    private PokemonRequest mapToDto(Pokemon pokemon) {
        PokemonRequest pokemonRequest = new PokemonRequest();
        pokemonRequest.setName(pokemon.getName());
        pokemonRequest.setType(pokemon.getType());
        pokemonRequest.setId(pokemon.getId());
        return pokemonRequest;
    }
}
