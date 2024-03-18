package com.mustafa.PokemonReviewApi.repository;

import com.mustafa.PokemonReviewApi.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
