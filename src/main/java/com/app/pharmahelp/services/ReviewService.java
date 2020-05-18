package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Reviews;
import com.app.pharmahelp.repository.ReviewRepo;
import com.app.pharmahelp.services.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    ReviewRepo reviewRepository;

    @Override
    public List<Reviews> getAllReviews() {
        List<Reviews> result = reviewRepository.findAll();
        return result;
    }

    @Override
    public Reviews saveReviews(Reviews review) {
        Reviews saved = this.reviewRepository.save(review);
        return saved;
    }

    @Override
    @Transactional
    public Reviews updateReviews(Long id, Reviews review) {
        Optional<Reviews> optional = reviewRepository.findById(id);
        Reviews result = optional.orElse(review);
        return result;
    }

    @Override
    public void deleteReviews(Long id) {
        reviewRepository.deleteById(id);

    }

    @Transactional
    public void validateReviews(long id,String status){
        reviewRepository.validate(id, status);
    }
}
