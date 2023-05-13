package com.pokemonreview.api.service;

import com.pokemonreview.api.data.model.Pokemon;
import com.pokemonreview.api.data.model.Review;
import com.pokemonreview.api.data.repository.ReviewRepository;
import com.pokemonreview.api.dto.request.ReviewRequest;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService{
    @Autowired
    PokemonService pokemonService;
    @Autowired
    ReviewRepository reviewRepository;


    public Review mapFromRequestToEntity(ReviewRequest reviewRequest){
        Review review = new Review();
        review.setId(reviewRequest.getId());
        review.setTitle(reviewRequest.getTitle());
        review.setContent(reviewRequest.getContent());
        review.setStart(reviewRequest.getStart());
        return review;
    }


    @Override
    public Review createReview(int pokemonId, ReviewRequest reviewRequest) {
        Review review = mapFromRequestToEntity(reviewRequest);
        Pokemon pokemon = pokemonService.getPokemonByI(pokemonId);
        if (pokemon == null) throw new PokemonNotFoundException("pokemon not found");
        review.setPokemon(pokemon);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByPokemonId(long pokemonId){
        List<Review> reviewList = reviewRepository.findByPokemonId(pokemonId);
        return reviewList.stream().collect(Collectors.toList());
    }

    @Override
    public Review findById(long PokemonId) {
        return reviewRepository.findById(PokemonId).orElseThrow
        (()-> new PokemonNotFoundException("review not found") );
    }


}
