package com.pokemonreview.api.service;

import com.pokemonreview.api.data.model.Review;
import com.pokemonreview.api.dto.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

   Review createReview(int pokemonId, ReviewRequest reviewRequest);
   List<Review> getReviewsByPokemonId(long pokemonId);
   Review findById(long PokemonId);
}
