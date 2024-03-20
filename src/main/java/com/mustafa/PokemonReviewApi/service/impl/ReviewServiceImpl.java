package com.mustafa.PokemonReviewApi.service.impl;

import com.mustafa.PokemonReviewApi.dto.ReviewDto;
import com.mustafa.PokemonReviewApi.excetion.PokemonNotFoundException;
import com.mustafa.PokemonReviewApi.excetion.ReviewNotFoundException;
import com.mustafa.PokemonReviewApi.models.Pokemon;
import com.mustafa.PokemonReviewApi.models.Review;
import com.mustafa.PokemonReviewApi.repository.PokemonRepository;
import com.mustafa.PokemonReviewApi.repository.ReviewRepository;
import com.mustafa.PokemonReviewApi.service.abstracts.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToReview(reviewDto);
        Pokemon pokemon = this.pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        review.setPokemon(pokemon);
        Review newReview = this.reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews = this.reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = this.pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {

    }
    public ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    public Review mapToReview(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setTitle(reviewDto.getTitle());
        return review;
    }
}
