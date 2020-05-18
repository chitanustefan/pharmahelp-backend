package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Reviews;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.DrugService;
import com.app.pharmahelp.services.ReviewService;
import com.app.pharmahelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private DrugService drugService;

    @GetMapping("/validated")
    public List<Reviews> getValidatedReviews(){
        List<Reviews> all = reviewService.getAllReviews();
        List<Reviews> validatedReviews = new ArrayList<>();

        for(Reviews r: all){
            if(r.getStatus().equals("approved")){
                validatedReviews.add(r);
            }
        }
        return validatedReviews;
    }

    @GetMapping("/nonvalidated")
    public List<Reviews> getNonValidatedReviews(){
        List<Reviews> all = reviewService.getAllReviews();
        List<Reviews> nonvalidated = new ArrayList<>();

        for(Reviews r: all){
            if(r.getStatus().equals("pending")){
                nonvalidated.add(r);
            }
        }
        return nonvalidated;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody Reviews review, @RequestParam long userID, @RequestParam long drugID){
        List<Reviews> allReviews = reviewService.getAllReviews();
        for (Reviews r: allReviews){
            if (r.getUser().getId_user() == userID && r.getDrug().getId_drug() == drugID){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can add only one review per drug");
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        List<User> usersList = userService.getAllUsers();
        List<Drug> drugList = drugService.getAllDrugs();

        Reviews newreview = new Reviews();
        newreview.setRating(review.getRating());
        newreview.setComment(review.getComment());
        newreview.setCreatedDate(date);
        newreview.setStatus("pending");
        for(User u : usersList){
            if(u.getId_user()==userID){
                newreview.setUser(u);
            }
        }
        for(Drug d : drugList){
            if(d.getId_drug()==drugID){
                newreview.setDrug(d);
            }
        }

        reviewService.saveReviews(newreview);

        return ResponseEntity.ok(new ApiResponse(true,"Review successfully added. Waiting for approval"));
    }

    @PutMapping("/validate")
    public ResponseEntity<?> validateReview(@RequestParam long id, @RequestParam String status)
    {
        reviewService.validateReviews(id,status);
        return ResponseEntity.ok(new ApiResponse(true,"Review successfully validated" ));
    }
}
