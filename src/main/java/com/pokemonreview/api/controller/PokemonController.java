package com.pokemonreview.api.controller;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.dto.request.PokemonRequest;
import com.pokemonreview.api.dto.responce.PokemonResponse;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
private PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

//@PostMapping("/pokemon/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<PokemonRequest> createPokemon(@RequestBody PokemonRequest pokemonRequest) {
//        return new ResponseEntity<>(pokemonService.createPokemon(pokemonRequest), HttpStatus.CREATED);
//    }

    @GetMapping("pokemon/{id}")
    public Pokemon getPokemon(@PathVariable Long id) {
        return new Pokemon(id, "squale", "water");
    }

    @PostMapping("pokemon/createh")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon ){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }

@PutMapping("/pokemon/{id}update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int pokemonId){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);

}

@DeleteMapping("pokemon/{id}delete")
    public ResponseEntity<String> deletePokemon(@PathVariable ("id") Long pokemonId){
    System.out.println(pokemonId);
    return ResponseEntity.ok("Pokemon deleted successfully");
}

}
