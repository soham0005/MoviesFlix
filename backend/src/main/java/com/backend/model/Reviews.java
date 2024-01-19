package com.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    private String reviewBody;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @ManyToOne
    @JoinColumn(name = "movie_review_id")
    private Movie movie;

    public Reviews(Long reviewId, String reviewBody, LocalDateTime createdTime, LocalDateTime updatedTime, Movie movie) {
        this.reviewId = reviewId;
        this.reviewBody = reviewBody;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.movie = movie;
    }

    public Reviews() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewId=" + reviewId +
                ", reviewBody='" + reviewBody + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", movie=" + (movie != null ? movie.getMovieId() : "null") + // Avoid infinite loop
                '}';
    }

}
