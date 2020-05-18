package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.Reviews;

import java.util.List;

public interface IReviewService {

    List<Reviews> getAllReviews();
    Reviews saveReviews(Reviews review);
    Reviews updateReviews(Long id, Reviews review);
    void deleteReviews(Long id);
}
