package com.example.UserService.UserService.service;

import com.example.UserService.UserService.dto.RatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

//@FeignClient(url="http://localhost:8082;http://localhost:8082",value ="Rating-service")
@FeignClient(name = "RATING-SERVICE")
public interface APIClient {
    @GetMapping("api/ratings/getById")
    RatingDto getRatingByUserId(@RequestParam("userId") String userId);


}