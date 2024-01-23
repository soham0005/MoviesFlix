package com.backend.services;

import com.backend.model.Movie;
import com.backend.model.Reviews;
import com.backend.repository.MovieRepository;
import com.backend.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewsService {
    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    MovieService movieService;


    public Reviews createNewReview(String reviewBody,String imdbId) throws Exception {
        Optional<Movie> optionalmovie = movieService.getSingleMovieByImdbId(imdbId);

        if(optionalmovie.isPresent()){
            Movie movie = optionalmovie.get();
            Reviews reviews = new Reviews();
            reviews.setReviewBody(reviewBody);
            reviews.setCreatedTime(LocalDateTime.now());
            reviews.setUpdatedTime(LocalDateTime.now());

            reviews.setMovie(movie);

            Reviews savedReviews = reviewsRepository.save(reviews);
            movie.getReviews().add(savedReviews);
            movieService.saveMovie(movie);
            return savedReviews;
        }
        else {
            // Handle the case where the movie with the provided IMDb ID is not found
            return null;
        }
    }
}
