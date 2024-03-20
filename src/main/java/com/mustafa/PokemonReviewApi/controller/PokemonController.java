package com.mustafa.PokemonReviewApi.controller;

import com.mustafa.PokemonReviewApi.dto.PokemonDto;
import com.mustafa.PokemonReviewApi.service.abstracts.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDto>> getAllPokemon(){
        return new ResponseEntity<>(this.pokemonService.getAllPokemon(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getByIdPokemon(@PathVariable int id){
        return ResponseEntity.ok(this.pokemonService.getByIdPokemon(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(this.pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable int id, @RequestBody PokemonDto pokemonDto){
        PokemonDto updatePokemonDto = this.pokemonService.updatePokemon(id,pokemonDto);
        return new ResponseEntity<>(updatePokemonDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable int id){
        this.pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon delete",HttpStatus.OK);
    }
}
