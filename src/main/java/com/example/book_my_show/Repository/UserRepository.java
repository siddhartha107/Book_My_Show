package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
