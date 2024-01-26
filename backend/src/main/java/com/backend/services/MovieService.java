package com.backend.services;

import com.backend.model.Movie;
import com.backend.model.Reviews;
import com.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void saveMovie(Movie movie){
        movieRepository.save(movie);
        System.out.println("Movie Saved");
    }

    public List<Reviews> getReviewsByImdbId(String imdbId){
        Optional<Movie> movie = movieRepository.findByImdbId(imdbId);

        if (movie.isEmpty()){
            return Collections.emptyList();
        }
        List<Reviews> reviews = movie.get().getReviews();

        return reviews;

    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getSingleMovieByImdbId(String imdbid){
        return movieRepository.findByImdbId(imdbid);
    }


}
