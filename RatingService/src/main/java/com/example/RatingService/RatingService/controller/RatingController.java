package com.example.RatingService.RatingService.controller;

import com.example.RatingService.RatingService.entity.Rating;
import com.example.RatingService.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/ratings")

public class RatingController {

   @Autowired
   private  RatingService ratingService;

   @PostMapping("/create")
    public Rating createRating(@RequestBody Rating rating) {
       String randomUserId = UUID.randomUUID().toString();
       rating.setRatingId(randomUserId);
       return ratingService.create(rating);
    }

    @GetMapping("/get-All")
    public List<Rating> getAllRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/getById")
    public Optional<Rating> getRatingByUserId(@RequestParam("userId") String userId) {
        return ratingService.getRatingByUserId(userId);
    }
}
