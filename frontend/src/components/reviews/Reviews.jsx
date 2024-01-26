import React from 'react'
import { useParams } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import { useEffect, useRef } from 'react';
import ReviewForm from '../reviewForm/ReviewForm';
import api from '../../api/axiosConfig';

const Reviews = ({ getSingleMovieData, movie, reviews, setReviews }) => {


    const revText = useRef();
    let params = useParams();
    const movieId = params.movieId;

    useEffect(() => {
        getSingleMovieData(movieId);
    }, [])

    const addReview = async (e) => {
        e.preventDefault();
    
        const rev = revText.current;
    
        try {
            const response = await api.post("/api/reviews", { reviewBody: rev.value, imdbId: movieId });
            console.log("Review Added");
            const createdReview = response.data;
            const updatedReviews = [...reviews, createdReview];
            rev.value = "";
            setReviews(updatedReviews);
        }
        catch (err) {
            console.error(err);
        }
    }
    
    return (
        // <h1>Review Page</h1>
        <Container>
            <Row>
                <Col><h3>Reviews</h3></Col>
            </Row>
            <Row className="mt-2">
                <Col>
                    <img src={movie?.poster} alt="No Poster To Dislpay" />
                </Col>
                <Col>
                    {
                        <>
                            <Row>
                                <Col>
                                    <ReviewForm handleSubmit={addReview} revText={revText} labelText="Write a Review?" />
                                </Col>
                            </Row>
                            <Row>
                                <Col>
                                    <hr />
                                </Col>
                            </Row>
                        </>
                    }

                    {
                        reviews?.map((r) => {
                            return (
                                <div key={r.reviewId}>
                                    <Row>
                                        <Col>{r.reviewBody}</Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <hr />
                                        </Col>
                                    </Row>
                                </div>
                            );
                        })
                    }

                </Col>
            </Row>
            <Row>
                <Col>
                    <hr />
                </Col>
            </Row>
        </Container>
    )
}

export default Reviews;


// {
//     reviews?.map((r) => {
//         return (
//             <>
//                 <Row>
//                     <Col>{r.reviewBody}</Col>
//                 </Row>
//                 <Row>
//                     <Col>
//                         <hr />
//                     </Col>
//                 </Row>
//             </>
//         )
//     })
// }