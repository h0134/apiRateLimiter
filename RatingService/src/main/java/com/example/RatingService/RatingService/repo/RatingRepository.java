package com.example.RatingService.RatingService.repo;

import com.example.RatingService.RatingService.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

    Optional<Rating> findByUserId(String s);
}
