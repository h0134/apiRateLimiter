package com.example.RatingService.RatingService.service;

import com.example.RatingService.RatingService.entity.Rating;
import com.example.RatingService.RatingService.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    public Rating create(Rating rating){
        return  ratingRepository.save(rating);
    }

    public List<Rating> getRatings(){
        return ratingRepository.findAll();

    }
    public Optional<Rating> getRatingByUserId(String userId){
        Optional<Rating> rating = ratingRepository.findByUserId(userId);
       return  rating;
    }

}
