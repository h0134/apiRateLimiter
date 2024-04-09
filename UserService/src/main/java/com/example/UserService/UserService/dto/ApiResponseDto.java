package com.example.UserService.UserService.dto;

import com.example.UserService.UserService.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {

    private CustomerDto customer;
    private RatingDto rating;
}
