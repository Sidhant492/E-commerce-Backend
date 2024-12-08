package com.example.SpringBootMVC.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
}
