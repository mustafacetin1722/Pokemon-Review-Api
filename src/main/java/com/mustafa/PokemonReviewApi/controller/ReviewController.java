package com.mustafa.PokemonReviewApi.controller;

import com.mustafa.PokemonReviewApi.dto.ReviewDto;
import com.mustafa.PokemonReviewApi.service.abstracts.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable int id ,@RequestBody ReviewDto reviewDto){
        ReviewDto saveReview = this.reviewService.createReview(id,reviewDto);
        return new ResponseEntity<>(saveReview, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public List<ReviewDto> getReviewsPokemonId(@PathVariable int id){
        return this.reviewService.getReviewsByPokemonId(id);
    }
    @GetMapping("/{reviewId}/{pokemonId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable int reviewId,@PathVariable int pokemonId){
        ReviewDto reviewDto = this.reviewService.getReviewById(reviewId,pokemonId);
        return new ResponseEntity<>(reviewDto,HttpStatus.OK);
    }
    @PutMapping("/{reviewId}/{pokemonId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int pokemonId,
                                                  @PathVariable int reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        ReviewDto updateReviewDto = this.reviewService.updateReview(pokemonId,reviewId,reviewDto);
        return new ResponseEntity<>(updateReviewDto,HttpStatus.OK);
    }
    @DeleteMapping("/{reviewId}/{pokemonId}")
    public ResponseEntity<String> deleteReview(@PathVariable int reviewId,@PathVariable int pokemonId){
        reviewService.deleteReview(reviewId,pokemonId);
        return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
    }
}
