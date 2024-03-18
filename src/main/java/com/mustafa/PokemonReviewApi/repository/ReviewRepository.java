package com.mustafa.PokemonReviewApi.repository;

import com.mustafa.PokemonReviewApi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
