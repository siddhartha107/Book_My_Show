package com.example.book_my_show.Models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalAmount;

    private String movieName;

    private LocalTime showTime;

    private String ticketId = UUID.randomUUID().toString();

    private LocalDate showDate;

    private String theaterName;

    private String bookedSeats;


    //This is child wrt user
    @ManyToOne
    @JoinColumn
    private User user;



    //Ticket is child wrt show
    @ManyToOne
    @JoinColumn
    private Show show;

}
