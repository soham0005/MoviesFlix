package com.backend.controllers;

import com.backend.model.Reviews;
import com.backend.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReviewsController {

    @Autowired
    ReviewsService reviewsService;

    @PostMapping("/api/reviews")
    public ResponseEntity<?> createReview(@RequestBody Map<String, String> payload) throws Exception {

        Reviews result = reviewsService.createNewReview(payload.get("reviewBody"),payload.get("imdbId"));

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

}
