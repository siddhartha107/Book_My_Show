package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat,Integer> {
}
