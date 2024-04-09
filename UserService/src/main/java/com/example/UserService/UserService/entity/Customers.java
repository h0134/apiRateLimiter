package com.example.UserService.UserService.entity;

//import com.example.UserService.UserService.dto.Rating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="micro_users")
public class Customers {
    @Id
    @Column(name = "ID")
    private  String userId;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "EMAIL")
    private String email;

    @Transient
    private int ratings;



}
