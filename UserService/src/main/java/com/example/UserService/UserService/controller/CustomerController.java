package com.example.UserService.UserService.controller;

import com.example.UserService.UserService.dto.ApiResponseDto;
import com.example.UserService.UserService.dto.RatingDto;
import com.example.UserService.UserService.entity.Customers;
import com.example.UserService.UserService.redis.RateLimit;
import com.example.UserService.UserService.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class CustomerController {
    private CustomerService customerService;

    private final RateLimit rateLimitService;


    public CustomerController(CustomerService customerService, RateLimit rateLimitService) {
        this.customerService = customerService;
        this.rateLimitService = rateLimitService;

    }

    @PostMapping("/add-user")
    public Customers saveUser(@RequestBody Customers user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return customerService.saveUser(user);
    }



    @GetMapping("/data")
    public ResponseEntity<ApiResponseDto> getUser(@RequestParam("userId") String userId) {
        if (rateLimitService.isAllowed(userId, 5, 60)) {
            ApiResponseDto apiResponseDto = customerService.getUser(userId);
            return ResponseEntity.ok(apiResponseDto);
        } else {
            System.out.println("Too many requests");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

        }
    }

    @GetMapping("/get-all")
    public List<Customers> getAllCustomers(){

        return customerService.getAllCustomers();
    }

}
