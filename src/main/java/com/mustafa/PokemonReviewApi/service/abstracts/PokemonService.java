package com.mustafa.PokemonReviewApi.service.abstracts;

import com.mustafa.PokemonReviewApi.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemon();
    PokemonDto getByIdPokemon(int id);
    PokemonDto updatePokemon(int id , PokemonDto pokemonDto);
    void deletePokemon(int id);
}
