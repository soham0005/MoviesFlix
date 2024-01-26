import React from 'react'
import Carousel from 'react-material-ui-carousel';
import { Paper } from '@mui/material';
import { useNavigate,Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay } from '@fortawesome/free-solid-svg-icons';
import Button from 'react-bootstrap/Button';
import './Hero.css';




const Hero = ({movies}) => {
  const navigate = useNavigate();
  
  function reviews(movieid){
    navigate(`/Reviews/${movieid}`);
  }

  return (
    <div className ='movie-carousel-container'>
    <Carousel>
      {
          movies?.map((movie) =>{
              return(
                  <Paper key={movie.imdbId}>
                      <div className = 'movie-card-container'>
                          <div className="movie-card" style={{"--img": `url(${movie.backdrops[0]})`}}>
                              <div className="movie-detail">
                                  <div className="movie-poster">
                                      <img src={movie.poster} alt="" />
                                  </div>
                                  <div className="movie-title">
                                      <h4>{movie.title}</h4>
                                  </div>
                                  <div className="movie-buttons-container">
                                      <Link to={`/Trailer/${movie.trailerLink.substring(movie.trailerLink.length - 11)}`}>
                                          <div className="play-button-icon-container">
                                              <FontAwesomeIcon className="play-button-icon"
                                                  icon = {faCirclePlay}
                                              />
                                          </div>
                                      </Link>

                                      <div className="movie-review-button-container">
                                          <Button variant ="info" onClick={() => reviews(movie.imdbId)} >Reviews</Button>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </Paper>
              )
          })
      }
    </Carousel>
  </div>
  )
}

export default Hero
