package com.backend.controllers;

import com.backend.model.Movie;
import com.backend.model.Reviews;
import com.backend.repository.MovieRepository;
import com.backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/api/all")
    ResponseEntity<List<Movie>> getAllMovies(){
        System.out.println("/api/all Get Request Called");
        List<Movie> movies = movieService.allMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @PostMapping("/api/new")
    ResponseEntity<String> addNewMovie(@RequestBody Movie movie){
        System.out.println("/api/new Post Request Called");

        try {
            movieService.saveMovie(movie);
            return new ResponseEntity<>("New Movie Added",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/api/all")
    ResponseEntity<String> saveAllMovies(@RequestBody List<Movie> movies){
        movieRepository.saveAll(movies);
        return new ResponseEntity<>("Movies Saved",HttpStatus.OK);
    }

    @GetMapping("/api/reviews/{imdbId}")
    public ResponseEntity<List<Reviews>> getMovieReviewsByImdbId(@PathVariable String imdbId){
        List<Reviews> reviews = movieService.getReviewsByImdbId(imdbId);

//        if(reviews.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }


    @GetMapping("/api/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable String imdbId) {
        System.out.println("/api/{imdbId} Get Request Called");

        try {
            Optional<Movie> movie = movieService.getSingleMovieByImdbId(imdbId);
            if (movie.isPresent()) {
                return new ResponseEntity<>(movie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
