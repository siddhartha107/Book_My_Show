package com.example.book_my_show.Models;


import com.example.book_my_show.Enums.ShowType;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private LocalDate showDate;



    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;


    @CreationTimestamp
    private Date createdOn;


    @CreationTimestamp
    private Date updatedOn;

    //This is child wrt the movie
    @ManyToOne
    @JoinColumn
    private Movie movie;



    //This is child wrt the theater
    @ManyToOne
    @JoinColumn
    private Theater theater;




    //Show is parent wrt ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> listOfBookedTickets = new ArrayList<>();




    //This is parent wrt the showseats
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeats = new ArrayList<>();
    


}
