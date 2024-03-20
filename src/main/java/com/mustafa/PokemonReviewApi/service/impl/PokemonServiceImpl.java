package com.mustafa.PokemonReviewApi.service.impl;

import com.mustafa.PokemonReviewApi.dto.PokemonDto;
import com.mustafa.PokemonReviewApi.excetion.PokemonNotFoundException;
import com.mustafa.PokemonReviewApi.models.Pokemon;
import com.mustafa.PokemonReviewApi.repository.PokemonRepository;
import com.mustafa.PokemonReviewApi.service.abstracts.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonDtoResponse = new PokemonDto();
        pokemonDtoResponse.setId(newPokemon.getId());
        pokemonDtoResponse.setName(newPokemon.getName());
        pokemonDtoResponse.setType(newPokemon.getType());
        return pokemonDtoResponse;
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemons = this.pokemonRepository.findAll();
        return pokemons.stream().map(pokemon -> mapToDto(pokemon)).collect(Collectors.toList());
    }

    @Override
    public PokemonDto getByIdPokemon(int id) {
        Pokemon pokemon = this.pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(int id, PokemonDto pokemonDto) {
        Pokemon pokemon = this.pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be update"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatePokemon = pokemonRepository.save(pokemon);
        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = this.pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be delete"));
        this.pokemonRepository.delete(pokemon);
    }

    private Pokemon mapToPokemon(PokemonDto pokemonDto){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
    private PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }
}
