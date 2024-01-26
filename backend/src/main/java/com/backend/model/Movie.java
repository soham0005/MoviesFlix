package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;
    private String imdbId;
    private String movieTitle;
    private String releaseDate;
    private String trailerLink;
    private String poster;

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    @Column(length = 1000)
    private List<String> backdrops = new ArrayList<>();
    private List<String> genre = new ArrayList<>();

//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore  // Add this annotation to break the recursion
    private List<Reviews> reviews = new ArrayList<>();


    public Movie() {
    }


    public Movie(Long movieId, String imdbId, String movieTitle, String releaseDate, String trailerLink, String poster, List<String> backdrops, List<String> genre, List<Reviews> reviews) {
        this.movieId = movieId;
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.poster = poster;
        this.backdrops = backdrops;
        this.genre = genre;
        this.reviews = reviews;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<String> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<String> backdrops) {
        this.backdrops = backdrops;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", imdbId='" + imdbId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", trailerLink='" + trailerLink + '\'' +
                ", poster='" + poster + '\'' +
                ", backdrops=" + backdrops +
                ", genre=" + genre +
                ", reviews=" + (reviews != null ? reviews.size() : "null") +
                '}';
    }


}
