import { useEffect, useState } from 'react';
import './App.css';
import api from './api/axiosConfig';
import Layout from './components/Layout';
import { Routes, Route } from 'react-router-dom';
import Home from './components/home/Home';
import NotFound from './components/notFound/NotFound';
import Trailer from './components/trailer/Trailer';
import Header from './components/header/Header';
import Reviews from './components/reviews/Reviews';

function App() {

  useEffect(() => {
    getMovies();
  }, []);

  const [movies, setMovies] = useState();
  const [movie,setMovie] = useState();
  const [reviews,setReviews] = useState([]);

  const getMovies = async () => {
    try {
      const response = await api.get("/api/all");
      setMovies(response.data);
      // console.log(response.data);
    }
    catch (error) {
      console.log(error);
    }
  }
  const getSingleMovieData = async (movieId) => {
    try 
    {
        const response = await api.get(`/api/${movieId}`);

        const singleMovie = response.data;

        setMovie(singleMovie);
        // console.log("Response----->",response.data);
        console.log('Single Movie-> ',singleMovie);

        const singleMovieReviews = await api.get(`/api/reviews/${singleMovie.imdbId}`);
        const result = singleMovieReviews.data;
        // console.log("Reviews of Single Movie--->> ",result);
        setReviews(result);
        console.log(result);
        

    } 
    catch (error) 
    {
      console.error(error);
    }
  }


  return (
    <div className="App">
    <Header/>
    <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home movies={movies} />} ></Route>
          <Route path="/Trailer/:ytTrailerId" element={<Trailer/>}></Route>
          <Route path="/Reviews/:movieId" element ={<Reviews getSingleMovieData = {getSingleMovieData} movie={movie} reviews ={reviews} setReviews = {setReviews} />}></Route>
          <Route path="*" element = {<NotFound/>}></Route>
        </Route>
    </Routes>

  </div>
  );

}

export default App;
