package com.example.SpringBootMVC.Repo;


import com.example.SpringBootMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
