package com.example.UserService.UserService.service;

import com.example.UserService.UserService.dto.ApiResponseDto;
import com.example.UserService.UserService.dto.CustomerDto;
import com.example.UserService.UserService.dto.RatingDto;
import com.example.UserService.UserService.entity.Customers;
//import com.example.UserService.UserService.dto.Rating;
import com.example.UserService.UserService.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    APIClient apiClient;
    private Logger logger= LoggerFactory.getLogger(CustomerService.class);
    public Customers saveUser(Customers user) {
        customerRepository.save(user);
        return  user;
    }
    public ApiResponseDto getUser(String id){
       Customers  user =  customerRepository.findById(id).get();

//        ResponseEntity <RatingDto> ratingsForUsers = restTemplate.getForEntity("http://localhost:8082/ratings/getById?userId=c129746e-49a3-4a0d-a5c5-0ded14a0934a", RatingDto.class);

//        RatingDto ratingDto= ratingsForUsers.getBody();

        RatingDto ratingDto = apiClient.getRatingByUserId(user.getUserId());
        CustomerDto customerDto= new CustomerDto(user.getUserId(),user.getName(),user.getEmail());

        ApiResponseDto apiResposeDto = new ApiResponseDto();
        apiResposeDto.setCustomer(customerDto);
        apiResposeDto.setRating(ratingDto);


        return apiResposeDto;
    }
    public List<Customers> getAllCustomers(){
        return customerRepository.findAll();
    }
}
