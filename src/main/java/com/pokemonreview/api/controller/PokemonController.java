package com.pokemonreview.api.controller;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.dto.request.PokemonRequest;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
private PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonRequest> createPokemon(@RequestBody PokemonRequest pokemonRequest) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonRequest), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.getPokemonByI(id));
    }

    @PostMapping("pokemon/createh")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon ){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }
    @GetMapping("getAll/pokemon")
    public ResponseEntity<List<PokemonRequest>> getAllPokemon(@RequestParam(value = "pageNumber", defaultValue = "0",
    required = false) int pageNumber,
     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

    return  new ResponseEntity<>(pokemonService.getAllPokemon( pageNumber,  pageSize), HttpStatus.OK);
    }

    @PutMapping("/pokemon/{id}update")
    public ResponseEntity<PokemonRequest> updatePokemon(@RequestBody PokemonRequest pokemonRequest, @PathVariable long id){
        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonRequest, id), HttpStatus.OK);

}

    @DeleteMapping("/pokemon/{id}delete")
    public ResponseEntity<String> deletePokemon(@PathVariable ("id") long pokemonId){
    pokemonService.deletePokemonById(pokemonId);
   return new ResponseEntity<>("pokemon delete successfully boneshaker", HttpStatus.OK);
}

}
