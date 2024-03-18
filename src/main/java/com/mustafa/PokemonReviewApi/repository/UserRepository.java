package com.mustafa.PokemonReviewApi.repository;

import com.mustafa.PokemonReviewApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
