package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    //This is the parent wrt theaterSeats
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();


    //This is parent wrt show
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}
